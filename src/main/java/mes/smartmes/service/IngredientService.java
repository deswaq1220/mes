package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.IngredientInput;
import mes.smartmes.entity.IngredientStock;
import mes.smartmes.entity.Ingredients;
import mes.smartmes.entity.Porder;
import mes.smartmes.repository.IngredientInputRepository;
import mes.smartmes.repository.IngredientStockRepository;
import mes.smartmes.repository.PorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

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
        Porder porder = porderRepository.findById(porderNo).get();
        System.out.println(porder);

        // 지금 보다 이전이면
        if (porder != null && porder.getPorderDate().isBefore(LocalDateTime.now())) {
            porder.setPorderStatus("입고완료");
            porderRepository.save(porder);
            System.out.println("입고완료" + porder.toString());

            // 자재 입고테이블에 인서트
            IngredientInput ingredientInput = new IngredientInput();
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
            System.out.println("찍어보자 : " +  ingredientInput.toString());



            // 현재 재고를 업데이트  // 저장 된 재고를 더하기
            IngredientStock ingredientStock = new IngredientStock();
            ingredientStock.setIngredientId(ingredientInput.getIngredientId());
            ingredientStock.setStockNo(ingredientStockRepository.findByStockNo());



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
