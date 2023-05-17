package com.example.mes.service;

import com.example.mes.entity.*;
import com.example.mes.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProdPlanService {

    private OrdersRepository ordersRepository;
    private IngredientStockRepository ingredientStockRepository;
    private PorderRepository porderRepository;
    private ProductionPlanRepository productionPlanRepository;
    private FinproductRepository finproductRepository;

    @Autowired
    public ProdPlanService(OrdersRepository ordersRepository, IngredientStockRepository ingredientStockRepository,
                           PorderRepository porderRepository, ProductionPlanRepository productionPlanRepository,
                           FinproductRepository finproductRepository) {
        this.ordersRepository = ordersRepository;
        this.ingredientStockRepository = ingredientStockRepository;
        this.porderRepository = porderRepository;
        this.productionPlanRepository = productionPlanRepository;
        this.finproductRepository = finproductRepository;
    }

    public void processOrder(String orderNo) {



        // 수주 관리 테이블에서 제품명과 수주수량 가져오기
        Orders order = ordersRepository.findByOrderNo(orderNo);
        String productId = order.getProduct_id();
        int orderQuantity = order.getOrder_quantity();
        System.out.println("------------------");
        System.out.println("제품id : " + productId);

        Finproduct finproduct = finproductRepository.findByProductId(productId);
        int haveFinquantity = finproduct.getFinProduct_quantity();
        int productQuantity = orderQuantity - haveFinquantity;
        System.out.println("완재품 재고량(box) :" + haveFinquantity);
        System.out.println("생산해야할 제품(box)양 : " + productQuantity);
        System.out.println("수주량(box) : " + orderQuantity);

        if (orderQuantity > haveFinquantity) {
            System.out.println("In-1");
            System.out.println("=========여기용========");
            //완재품 재고량 빼기
            if(haveFinquantity > 0) {
                System.out.println("완재품빼기 왔어");
                finproductRepository.decreaseStockQuantity(finproduct.getProduct_id(), order.getOrder_quantity());
                if(productQuantity > 0){
                    System.out.println("완재품이 0보다 작을때 0으로 set하기 왔어");
                    finproduct.setFinProduct_quantity(0);
                    finproductRepository.save(finproduct); // 완재품 재고량 저장
                    System.out.println(finproduct.getFinProduct_quantity());

                }
            }
            // 수주수량(box 단위)을 원자재 kg 단위로 치환
            int needMaterials = (productQuantity * 30) / 20;

            System.out.println("수주수량 : " + needMaterials + "kg");

            // 재고관리 테이블에서 해당 원자재명에 대한 재고 수량 가져오기
            IngredientStock ingredientStock = ingredientStockRepository.findByProductId(productId);
            int haveMaterials = ingredientStock.getQuantity();
            System.out.println("원자재 재고량 : " + haveMaterials + "kg");

            // 발주량 계산
            int orderMaterials = needMaterials - haveMaterials; // 500 - 100 = 400(ordermaterials), 100 - 500 = -400(ordermaterials)
            System.out.println("발주량 : " + orderMaterials + "kg");

            if (needMaterials > haveMaterials) {
                // 발주 관리 테이블에 insert
                System.out.println("----------여기--------");
                Porder porder = new Porder();
                String porderno = generatePorderNumber();
                porder.setPorder_no(porderno);
                porder.setIngredient_id(productId);
                //최소주문량 계산해서 적용하기(해야함)
                porder.setPorder_quantity(orderMaterials);
                // 품목명, 수량(orderMaterials), 납품예정일(계산) 설정
                // 예를 들어, 7일 후로 설정하는 경우:
                Date porderDate = Calendar.getInstance().getTime();
                porder.setPorder_date(porderDate);
                porderRepository.save(porder);
                porderRepository.flush();


                    // 생산계획 테이블에 insert
                    ProductionPlan productionPlan = new ProductionPlan();
                    IngredientStock ig = new IngredientStock();
                    String planno = generatePlanNumber();
                System.out.println("1차생산 플랜번호 : "+ planno);
                    productionPlan.setProdPlan_no(planno);
                    productionPlan.setOrder_no(orderNo);
                    Date planDate = Calendar.getInstance().getTime();
                    productionPlan.setProdPlan_date(planDate);
                    Optional<Integer> maxSeqOptional = Optional.ofNullable(productionPlanRepository.getMaxProdPlanSeqByOrderNo(orderNo));
                    int maxSeq = maxSeqOptional.orElse(0);
                    int nextSeq = maxSeq + 1;
                    productionPlan.setProdPlan_seq(nextSeq);
                    productionPlan.setProduct_id(productId);
                    //productionPlan.setProdPlan_quantity(haveMaterials);
                    System.out.println(productionPlan.getProdPlan_quantity());

                    // 진행상태(생산계획 테이블 로우에 진행중이 있는 경우 대기 or 없는 경우 진행중으로) 설정
                    // 예를 들어, 대기 상태로 설정하는 경우:
                    String progressStatus = determineProgressStatus(productId);
                    productionPlan.setProdPlan_fin_yn(progressStatus);
                    ig.setProduct_id(productId);
                    //ig.setQuantity(needMaterials);
                    // 첫 번째 생산계획의 수량 설정
                    productionPlan.setProdPlan_quantity(haveMaterials);
                    ingredientStockRepository.decreaseStockQuantity(ig.getProduct_id(), productionPlan.getProdPlan_quantity());
                    ordersRepository.setOrderStatus(orderNo, "C");
                    productionPlanRepository.save(productionPlan);
                    productionPlanRepository.flush();

                if(haveMaterials != 0) {
                    // 두 번째 생산계획의 수량 설정
                    int remainingMaterials = needMaterials - haveMaterials;
                    if (remainingMaterials > 0) {
                        ProductionPlan productionPlan2 = new ProductionPlan();
                        String planno2 = generatePlanNumber();
                        System.out.println("2차생산 플랜번호 : "+ planno2);
                        productionPlan.setProdPlan_no(planno2);
                        productionPlan.setOrder_no(orderNo);
                        productionPlan.setProdPlan_date(planDate);
                        int nextSeq2 = nextSeq + 1;
                        productionPlan.setProdPlan_seq(nextSeq2);
                        productionPlan.setProduct_id(productId);
                        productionPlan.setProdPlan_quantity(remainingMaterials);
                        productionPlan.setProdPlan_fin_yn("발주입고대기중");
                        //원자재 재고량 빼기
                        if (haveMaterials < 0) {
                            ingredientStockRepository.decreaseStockQuantity(ig.getProduct_id(), productionPlan.getProdPlan_quantity());
                        }
                        productionPlanRepository.save(productionPlan);
                        productionPlanRepository.flush();
                    }
                }

            } else {
                // 생산계획 테이블에 insert
                System.out.println("----------여기2--------");
                ProductionPlan productionPlan = new ProductionPlan();
                IngredientStock ig = new IngredientStock();
                String planno1 = generatePlanNumber();
                productionPlan.setProdPlan_no(planno1);
                productionPlan.setOrder_no(orderNo);
                Date planDate = Calendar.getInstance().getTime();
                productionPlan.setProdPlan_date(planDate);
                productionPlan.setProduct_id(productId);
                productionPlan.setProdPlan_quantity(needMaterials);
                System.out.println("생산계획수량 : "+productionPlan.getProdPlan_quantity());
                // 진행상태(생산계획 테이블 로우에 진행중이 있는 경우 대기 or 없는 경우 진행중으로) 설정
                // 예를 들어, 대기 상태로 설정하는 경우:
                String progressStatus = determineProgressStatus(productId);
                productionPlan.setProdPlan_fin_yn(progressStatus);
                // prodplan_seq 할당
                Optional<Integer> maxSeqOptional = Optional.ofNullable(productionPlanRepository.getMaxProdPlanSeqByOrderNo(orderNo));
                int maxSeq = maxSeqOptional.orElse(0);
                int nextSeq = maxSeq + 1;
                productionPlan.setProdPlan_seq(nextSeq);
                ig.setProduct_id(productId);
                ig.setQuantity(needMaterials);
                //완재품 재고량 빼기
                //finproductRepository.decreaseStockQuantity(finproduct.getProduct_id(), order.getOrder_quantity());
                //원자재 재고량 빼기
                ingredientStockRepository.decreaseStockQuantity(ig.getProduct_id(), productionPlan.getProdPlan_quantity());
                ordersRepository.setOrderStatus(orderNo,"C");
                productionPlanRepository.save(productionPlan);
                productionPlanRepository.flush();
            }
        }else{
            System.out.println("In-2");
            //완재품 재고량 빼기
            finproductRepository.decreaseStockQuantity(finproduct.getProduct_id(), order.getOrder_quantity());
            ordersRepository.setOrderStatus(orderNo,"C");
            System.out.println("완재품 재고량(box) :" + haveFinquantity);
            System.out.println("완제품량이 충분하므로 바로 출고");
        }
    }



    private String determineProgressStatus(String productId) {
        // 생산계획 테이블에서 해당 제품의 진행중인 로우를 조회하는 쿼리를 실행
        List<ProductionPlan> productionPlans = productionPlanRepository.findByProductIdAndProdPlanFinYn(productId, "진행중");

        if (productionPlans.isEmpty()) {
            return "진행중"; // 진행 중인 로우가 없는 경우 "진행중" 상태 반환
        } else {
            return "대기"; // 진행 중인 로우가 있는 경우 "대기" 상태 반환
        }
    }

    @Scheduled(cron = "*/30 * * * * ?") // 30초 마다 실행
    public void processOrdersAutomatically() {
        List<Orders> orders = ordersRepository.findByOrderStatus("B");
        if (orders != null && !orders.isEmpty()) {
            for (Orders order : orders) {
                int orderQuantity = order.getOrder_quantity();
                String orderNo = order.getOrder_no();
                processOrder(orderNo);
                // 주문 처리 후 상태를 "C"로 변경
//                ordersRepository.setOrderStatus(orderNo,"C");
//                ordersRepository.save(order);


//                int orderQuantity = order.getOrder_quantity();
//                String orderNo = order.getOrder_no();
//                int needMaterials = (orderQuantity * 30) / 20;
//
//                ProductionPlan productionPlan = productionPlanRepository.findByOrderNo(orderNo);
//                IngredientStock ig = new IngredientStock();
//
//                if (productionPlan == null || productionPlan.getProdPlan_quantity() != needMaterials) {
//                    processOrder(orderNo);
//                }else if(ig.getQuantity() < needMaterials){
//                    System.out.println("=====================");
//                    System.out.println("재고수량을 확인하세요!!!");
//                    System.out.println("=====================");
//
//                }

            }
        }
    }

    private static int sequence = 1;
    private static int sequence1 = 1;

    public String generatePlanNumber() {
        // 현재 시간 정보를 가져옵니다.
        LocalDateTime now = LocalDateTime.now();
        // 형식 지정을 위한 DateTimeFormatter를 생성합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 현재 시간 정보를 "yyyyMMdd" 형식의 문자열로 변환합니다.
        String formattedDate = now.format(formatter);
        // 시퀀스 값을 문자열로 변환합니다.
        String formattedSequence = String.format("%03d", sequence);
        // 시퀀스 값을 1 증가시킵니다.
        sequence++;
        // 생산계획번호를 조합하여 반환합니다.
        return "PP" + formattedDate + formattedSequence;
    }

    public String generatePorderNumber() {
        // 현재 시간 정보를 가져옵니다.
        LocalDateTime now = LocalDateTime.now();
        // 형식 지정을 위한 DateTimeFormatter를 생성합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 현재 시간 정보를 "yyyyMMdd" 형식의 문자열로 변환합니다.
        String formattedDate = now.format(formatter);
        // 시퀀스 값을 문자열로 변환합니다.
        String formattedSequence = String.format("%03d", sequence1);
        // 시퀀스 값을 1 증가시킵니다.
        sequence1++;
        // 생산계획번호를 조합하여 반환합니다.
        return "PD" + formattedDate + formattedSequence;
    }
}


