package com.example.mes.repository;

import com.example.mes.entity.IngredientStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientStockRepository extends JpaRepository<IngredientStock, String> {

    @Query("SELECT i FROM IngredientStock i WHERE i.product_id = :productId")
    IngredientStock findByProductId(String productId);

    @Transactional
    @Modifying
    @Query("UPDATE IngredientStock ig SET ig.quantity = ig.quantity - :quantity WHERE ig.product_id = :product_id")
    void decreaseStockQuantity(String product_id, int quantity);


}
