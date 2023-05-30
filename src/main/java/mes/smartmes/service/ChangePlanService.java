package mes.smartmes.service;

import mes.smartmes.entity.ProductionPlan;
import mes.smartmes.repository.ProductionPlanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ChangePlanService {
    private ProductionPlanRepository productionPlanRepository;

    public ChangePlanService(ProductionPlanRepository productionPlanRepository) {
        this.productionPlanRepository = productionPlanRepository;
    }

    public void processOrder(String planNo){
        // 작업지시완료 데이터를 가진 로우를 조회
        System.out.println("안녕하세요 여기");
        ProductionPlan completedPlan = productionPlanRepository.findByProdPlanFinYn1("작업지시완료(원자재출하완료)");
        System.out.println("안녕하세요~~~ = "+completedPlan);
        if (completedPlan != null) {
            // 다음 로우를 조회하여 대기중에서 진행중으로 변경
            ProductionPlan nextPlan = productionPlanRepository.findNextPlan(completedPlan.getProdPlanNo());
            System.out.println("안녕하세용ㅇㅇㅇ = "+nextPlan);
            if (nextPlan != null) {
                nextPlan.setProdPlanFinYn("진행중");
                productionPlanRepository.save(nextPlan);
            }
        }
    }


    @Scheduled(cron = "*/15 * * * * ?") // 30초 마다 실행
    public void processOrdersAutomatically() {
        List<ProductionPlan> plans = productionPlanRepository.findByProdPlanFinYn("작업지시완료(원자재출하완료)");
        if (plans != null && !plans.isEmpty()) {
            for (ProductionPlan plan : plans) {
                int planQuantity = plan.getProdPlanQuantity();
                String planNo = plan.getProdPlanNo();
                System.out.println("안녕하세요 여기~~~");
                processOrder(planNo);
            }
        }

    }
}
