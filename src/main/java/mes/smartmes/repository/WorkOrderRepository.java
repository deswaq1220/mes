package mes.smartmes.repository;


import mes.smartmes.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, String> {

    @Query("SELECT MAX(w.workOrderSeq) FROM WorkOrder w WHERE w.prodPlanNo = :planNo")
    Integer getMaxWorkPlanSeqByPlanNo(@Param("planNo") String planNo);

}