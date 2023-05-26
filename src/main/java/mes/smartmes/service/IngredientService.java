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


                // stockNo 생성
                String dayNo = "ST" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                int orderIntNo=0;

                // 값이 없을 시 값 시작 값 생성
                if (ingredientStockRepository.findByStockNo() == null) {
                    orderIntNo = 1;
                    String stockNo = dayNo + String.format("%04d", orderIntNo);

                    ingredientStock.setStockNo(stockNo);
                    System.out.println("스톡남바" + ingredientStock.getStockNo());
                    System.out.println("인그리언트남바" + ingredientStock.getIngredientId());
                    ingredientStockRepository.save(ingredientStock);
                } else {
                    orderIntNo = Integer.parseInt(ingredientStockRepository.findByStockNo()) + 1;
                    String stockNo = dayNo + String.format("%04d", orderIntNo);
                    ingredientStock.setStockNo(stockNo);
                    ingredientStockRepository.save(ingredientStock);
                }






                ingredientStock.setIngredientId(ingredientInput.getIngredientId());
//            ingredientStockRepository.increaseStockQuantity();
//            ingredientStock.setInputDate(ingredientInput.getInputDate());
                ingredientStock.setProductDate(new Date(System.currentTimeMillis()));

                ingredientStock.setQuantity(ingredientInput.getInputQuantity());

                ingredientStockRepository.save(ingredientStock);


            }

        }
    }


       

    
}
