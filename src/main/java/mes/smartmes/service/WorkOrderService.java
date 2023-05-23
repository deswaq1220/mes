package mes.smartmes.service;

import mes.smartmes.entity.ProductionPlan;
import mes.smartmes.repository.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderService {

        private ProductionPlanRepository productionPlanRepository;


        @Autowired
        private ProdPlanService productService;

        public WorkOrderService(ProductionPlanRepository productionPlanRepository){
            this.productionPlanRepository = productionPlanRepository;
        }

        public void processOrder(String planNo){

        }

        @Scheduled(cron = "*/30 * * * * ?") // 30초 마다 실행
        public void processOrdersAutomatically() {
            List<ProductionPlan> plans = productionPlanRepository.findByProdPlanFinYn("진행중");
            if (plans != null && !plans.isEmpty()) {
                for (ProductionPlan plan : plans) {
                    int planQuantity = plan.getProdPlanQuantity();
                    String planNo = plan.getProdPlanNo();
                    processOrder(planNo);


                }
            }
        }
    }
