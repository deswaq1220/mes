package mes.smartmes.repository;

import mes.smartmes.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProdPlanRepository extends JpaRepository<ProductionPlan, String> {

    List<ProductionPlan> findAll();

    //prodPlanDate가 startDate와 endDate 사이에 있는지
    @Query("SELECT p FROM ProductionPlan p WHERE p.prodPlanDate BETWEEN :startDate AND :endDate AND p.prodPlanFinYn = :status AND p.productName = :items")
    List<ProductionPlan> findSearch(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("status") String status, @Param("items") String items);

}



