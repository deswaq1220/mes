package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.*;
import mes.smartmes.repository.IngredientInputRepository;
import mes.smartmes.repository.IngredientStockRepository;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.PorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

    @PersistenceContext
    private EntityManager em;

    // 재고와 ingredientId의 매핑 정보를 관리하기 위한 맵
    private Map<String, String> ingredientStockMapping;



    private IngredientInputRepository ingredientInputRepository;
    private IngredientStockRepository ingredientStockRepository;

    private PorderRepository porderRepository;

    @Autowired
    public IngredientService(IngredientInputRepository ingredientInputRepository, IngredientStockRepository ingredientStockRepository, PorderRepository porderRepository) {
        this.ingredientInputRepository = ingredientInputRepository;
        this.ingredientStockRepository = ingredientStockRepository;
        this.porderRepository = porderRepository;
    }

    // 발주 재료 입고  업데이트 확인
    @Transactional
    public void updatePorderStatusAndInsertIngredient(String porderNo) {
        List<Porder> porders = porderRepository.findAll();

        for(Porder porder : porders) {
            //재고 넘버링 부여할 스트링 필드
            String selectStockNo = ingredientStockRepository.selectMaxStockNo();
            // 자재 입고 테이블
            IngredientInput ingredientInput = new IngredientInput();

            // 자재 테이블
            IngredientStock ingredientStock = new IngredientStock();



            // 지금 보다 이전이면
            System.out.println("일단 제일 첫 번째" + porder.getPorderStatus());


            if (porder != null && porder.getPorderDate().isBefore(LocalDateTime.now()) && porder.getPorderStatus().equals("입고대기")) {
                porder.setPorderStatus("입고완료");
                porderRepository.save(porder);
                System.out.println("입고완료" + porder.toString());

                // 자재 입고테이블에 인서트

                ingredientInput.setPorderNo(porder.getPorderNo());              // 발주번호
//            System.out.println("ingredientInput1 = " + ingredientInput);
                ingredientInput.setIngredientId(porder.getIngredientId());   // 발주 된 제품명
//            System.out.println("ingredientInput2 = " + ingredientInput);
                ingredientInput.setInputQuantity(porder.getPorderQuantity());    // 발주 수량
//            System.out.println("ingredientInput3 = " + ingredientInput);
                ingredientInput.setInputDate(LocalDateTime.now());
                ingredientInput.setIngredientId(porder.getIngredientId());
                System.out.println("ingredientInput4 = " + ingredientInput);
                ingredientInputRepository.save(ingredientInput);
                System.out.println(ingredientInput.getIngredientInId());
                System.out.println(ingredientInput.getPorderNo());
                System.out.println("찍어보자 : " + ingredientInput.toString());


                // 현재 재고를 업데이트  // 저장 된 재고를 더하기

                System.out.println("이건 리스트고" + porder.toString());
                System.out.println("이건 뭐고2 " + porder.getIngredientId());


            }
        }
        System.out.println("발주번호"+porderNo);
        Porder porder = porderRepository.findByPorderNo(porderNo);

        int porderQ = porder.getPorderQuantity(); //발주한 량
        String ingreId = porder.getIngredientId();
        ingredientStockRepository.increaseStockQuantity(ingreId, porderQ);
        porder.setPorderStatus("적재완료");
        porderRepository.save(porder);
        porderRepository.flush();

    }






    // ingredientId 를 조회하여 IngredientStock의 ingredientId에 해당하는 모든 quantity를 더하는 메서드
//    public Long getTotalQuantityByIngredientId(String ingredientId) {
//
//        // 조건에 따라  더한다
//
//        //  sql 문법을 jpa에 맞게  저장해서
//        String jpql = "SELECT SUM(e.quantity) FROM IngredientStock e WHERE e.ingredientId = :ingredientId";
//        // 여기다 넣고
//        Query query = em.createQuery(jpql);
//
//
//        // 쿼리에 값을 넣어  주고
//        query.setParameter("ingredientId", ingredientId);
//
//        // 집계 함수는 단 하나의 값만 나온다. // 섬의 값을 가져 오기 위해서 써줌
//        Long totalQuantity = (Long)query.getSingleResult();
//
//        System.out.println("이건 뭐라 찍히노 " + ingredientId + "   " + query + "   " + totalQuantity);
//        return totalQuantity;
//    }


    @Scheduled(cron = "*/30 * * * * ?") // 30초마다 실행
    public void processOrdersAutomatically() {
        List<Porder> porders = porderRepository.findByPorderStatus("입고완료");
        if (porders != null && !porders.isEmpty()) {
            for (Porder porder : porders) {
                //int porderQuantity = porder.getPorderQuantity();
                String porderNo = porder.getPorderNo();
                updatePorderStatusAndInsertIngredient(porderNo);
            }
        }


    }

    //

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




    // 모든 발주 동작
   /* public void AllPorders() {
        List<Porder> porders = porderRepository.findAll();
        IngredientStock ingredientStock = new IngredientStock();
        ingredientStock.setIngredientId(ingredientInput.getIngredientId());
        ingredientStock.setStockNo(ingredientStockRepository.findByStockNo());

        // 하나씩 돌아가며
        for (Porder porder : porders) {
            //porderNo 겟
            String porderNo = porder.getPorderNo();
            if (porder.getPorderStatus().equals("입고대기")){
                ingredientStock.setIngredientId(ingredientInput.getIngredientId());
//            ingredientStockRepository.increaseStockQuantity();
//            ingredientStock.setInputDate(ingredientInput.getInputDate());
                ingredientStock.setProductDate(new Date(System.currentTimeMillis()));

                ingredientStock.setQuantity(ingredientInput.getInputQuantity());

                ingredientStockRepository.save(ingredientStock);

                // 발주 완료 및 재고 추가 메서드 동작
                updatePorderStatusAndInsertIngredient(porderNo);
            } else{
                continue;
            }
        }
    }*/



    // 초기화 시점에 ingredientStockMapping 맵을 생성하고 정보 저장
    @PostConstruct
    public void initStockMapping() {
        ingredientStockMapping = new HashMap<>();
        ingredientStockMapping.put("I001", "S001");     // I001 양배추
        ingredientStockMapping.put("I002", "S002");     // I002 흑마늘
        ingredientStockMapping.put("I003", "S003");     // I003 석류농축액
        ingredientStockMapping.put("I004", "S004");     // I004 매실농축액
        ingredientStockMapping.put("I005", "S005");     // I005 콜라겐
        ingredientStockMapping.put("I006", "S006");     // 파우치
        ingredientStockMapping.put("I007", "S007");     // 스틱 파우치
        ingredientStockMapping.put("I008", "S008");     // 포장 Box
    }

    // ingredientId에 대응하는 stockNo를 반환하는 메서드
    private String getReturnStockNo(String ingredientId) {
        return ingredientStockMapping.get(ingredientId);
    }



}