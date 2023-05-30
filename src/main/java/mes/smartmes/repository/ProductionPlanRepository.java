package mes.smartmes.repository;


import mes.smartmes.entity.Orders;
import mes.smartmes.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String> {

    @Query("SELECT p FROM ProductionPlan p WHERE p.productId = :productId")
    List<ProductionPlan> findByProductId(@Param("productId")String productId);

    @Query("SELECT o FROM ProductionPlan o WHERE o.prodPlanNo = :planNo")
    ProductionPlan findByPlanNo(@Param("planNo")String planNo);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ProductionPlan (product_id, prodPlan_quantity, prodPlan_fin_yn) " +
            "VALUES (:productId, :quantity, :status)", nativeQuery = true)
    void insertProductionPlan(@Param("productId") String productId, @Param("quantity") int quantity, @Param("status") String status);

    @Query("SELECT p FROM ProductionPlan p WHERE p.prodPlanFinYn = :prodPlanFinYn")
    List<ProductionPlan> findByProdPlanFinYn(@Param("prodPlanFinYn") String prodPlanFinYn);

    @Query("SELECT MAX(p.prodPlanSeq) FROM ProductionPlan p WHERE p.orderNo = :orderNo")
    Integer getMaxProdPlanSeqByOrderNo(@Param("orderNo") String orderNo);

    @Query("SELECT o FROM Orders o WHERE o.orderStatus = :orderStatus")
    List<Orders> findByOrderStatus(@Param("orderStatus") String orderStatus);

    @Modifying
    @Transactional
    @Query("UPDATE ProductionPlan p SET p.prodPlanFinYn = :planStatus WHERE p.prodPlanNo = :planNo")
    void setPlanStatus(@Param("planNo") String orderNo, @Param("planStatus") String planStatus);



}