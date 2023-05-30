package mes.smartmes.service;

import mes.smartmes.entity.IngredientOutput;
import mes.smartmes.entity.IngredientStock;
import mes.smartmes.entity.ProductionPlan;
import mes.smartmes.repository.IngredientStockRepository;
import mes.smartmes.repository.IngredientsOutputRepository;
import mes.smartmes.repository.PorderRepository;
import mes.smartmes.repository.ProductionPlanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class IngredientOutputService {

    private ProductionPlanRepository productionPlanRepository;
    private PorderRepository porderRepository;
    private IngredientStockRepository ingredientStockRepository;
    private IngredientsOutputRepository ingredientsOutputRepository;


    public IngredientOutputService(ProductionPlanRepository productionPlanRepository, PorderRepository porderRepository, IngredientStockRepository ingredientStockRepository, IngredientsOutputRepository ingredientsOutputRepository){
        this.productionPlanRepository = productionPlanRepository;
        this.porderRepository = porderRepository;
        this.ingredientStockRepository = ingredientStockRepository;
        this.ingredientsOutputRepository = ingredientsOutputRepository;
    }

    public void changePlan(String orderNo, String planNo){

        List<String> po = porderRepository.findByNo(orderNo,"입고완료");
        List<Integer> pq = porderRepository.findByNo1(orderNo,"입고완료");
        System.out.println("체인지"+po);
        ProductionPlan pp = productionPlanRepository.findByPlanNo(planNo);
        IngredientOutput io = new IngredientOutput();
        String id = null;

        for (int i= 0; i<po.size(); i++) {
            if (po.get(i).equals("양배추")) {
                id = "I001";
            } else if (po.get(i).equals("흑마늘")) {
                id = "I002";
            } else if (po.get(i).equals("석류농축액")) {
                id = "I003";
            } else if (po.get(i).equals("매실농축액")) {
                id = "I004";
            } else if (po.get(i).equals("콜라겐")) {
                id = "I005";
            } else if (po.get(i).equals("파우치")) {
                id = "I006";
            } else if (po.get(i).equals("스틱파우치")) {
                id = "I007";
            } else if (po.get(i).equals("포장Box")) {
                id = "I008";
            }
            IngredientStock is = ingredientStockRepository.findByIngId(id);
            if(id.equals("I001")){
                is.setQuantity(is.getQuantity()-pq.get(i));
            }
            is.setQuantity(is.getQuantity()-pq.get(i));
            if(is.getQuantity()-pq.get(i) <= 0){
                is.setQuantity(0);
            }
            io.setIngredientId(id);
            io.setOutputDate(LocalDateTime.now());
            String no = generateOutputNumber();
            io.setIngredientOutId(no);
            io.setPorderNo(planNo);
            io.setOutputQuantity(pq.get(i));
            ingredientsOutputRepository.save(io);
            ingredientsOutputRepository.flush();
            ingredientStockRepository.save(is);

        }
        pp.setProdPlanFinYn("작업지시완료(원자재출하완료)");
        productionPlanRepository.save(pp);
        productionPlanRepository.flush();
        ingredientStockRepository.flush();






    }




    @Scheduled(cron = "*/20 * * * * ?") // 30초 마다 실행
    public void processPlanChangeAutomatically() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<ProductionPlan> plans = productionPlanRepository.findByProdPlanFinYn("작업지시완료");
        if (plans != null && !plans.isEmpty()) {
            for (ProductionPlan productionPlan : plans) {
                String orderNo = productionPlan.getOrderNo();
                String planNo = productionPlan.getProdPlanNo();
                changePlan(orderNo,planNo);

            }
        }

    }

    private static int sequence = 1;
    public String generateOutputNumber() {
        // 현재 시간 정보를 가져옵니다.
        LocalDateTime now = LocalDateTime.now();
        // 형식 지정을 위한 DateTimeFormatter를 생성합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 현재 시간 정보를 "yyyyMMdd" 형식의 문자열로 변환합니다.
        String formattedDate = now.format(formatter);
        // 시퀀스 값을 문자열로 변환합니다.
        String formattedSequence = String.format("%03d", sequence);

        List<String> no = ingredientsOutputRepository.findingredientOutId();
        for(int i=0;i<no.size();i++){
            if(("SM"+formattedDate+formattedSequence) == no.get(i)){
                int incrementedValue = Integer.parseInt(formattedSequence) + 1;
                formattedSequence = String.format("%03d", incrementedValue);
            }
        }

        // 시퀀스 값을 1 증가시킵니다.
        sequence++;
        // 생산계획번호를 조합하여 반환합니다.
        return "IO" + formattedDate + formattedSequence;
    }
}
