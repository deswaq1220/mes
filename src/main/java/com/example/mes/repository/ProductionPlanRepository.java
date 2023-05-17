package com.example.mes.repository;

import com.example.mes.entity.Orders;
import com.example.mes.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, String> {

    @Query("SELECT p FROM ProductionPlan p WHERE p.product_id = :productId")
    List<ProductionPlan> findByProductId(String productId);

    @Query("SELECT o FROM ProductionPlan o WHERE o.order_no = :orderNo")
    ProductionPlan findByOrderNo(String orderNo);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ProductionPlan (product_id, prodPlan_quantity, prodPlan_fin_yn) " +
            "VALUES (:productId, :quantity, :status)", nativeQuery = true)
    void insertProductionPlan(@Param("productId") String productId, @Param("quantity") int quantity, @Param("status") String status);

    @Query("SELECT p FROM ProductionPlan p WHERE p.product_id = :productId AND p.prodPlan_fin_yn = :status")
    List<ProductionPlan> findByProductIdAndProdPlanFinYn(@Param("productId") String productId, @Param("status") String status);

    @Query("SELECT MAX(p.prodPlan_seq) FROM ProductionPlan p WHERE p.order_no = :orderNo")
    Integer getMaxProdPlanSeqByOrderNo(@Param("orderNo") String orderNo);



}
