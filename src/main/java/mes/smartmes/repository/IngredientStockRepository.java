package mes.smartmes.repository;

import mes.smartmes.entity.IngredientStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.Optional;

public interface IngredientStockRepository extends JpaRepository<IngredientStock, String> {


    List<IngredientStock> findAll();


    // 현일
    @Query("SELECT i FROM IngredientStock i WHERE i.productId = :productId")
    IngredientStock findByProductId(String productId);

    @Transactional
    @Modifying
    @Query("UPDATE IngredientStock ig SET ig.quantity = ig.quantity - :quantity WHERE ig.productId = :product_id")
    void decreaseStockQuantity(String product_id, int quantity);

    @Transactional
    @Modifying
    @Query("UPDATE IngredientStock ig SET ig.quantity = ig.quantity - :quantity WHERE ig.ingredientId = :ingredientId")
    void decreaseStock1Quantity(String ingredientId, int quantity);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I008'")
    int findCabbageBoxConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I008'")
    int findBlackBoxConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I008'")
    int findRaspberryBoxConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I008'")
    int findPlumBoxConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I006'")
    int findCabbagePouchConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I006'")
    int findBlackPouchConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I007'")
    int findRaspberryStickPouchConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I007'")
    int findPlumStickPouchConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I005'")
    int findPlumCollagenConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.productId = :productId AND i.ingredientId = 'I005'")
    int findRaspberryCollagenConcentrateByProductId(@Param("productId") String productId);

    @Query("SELECT i FROM IngredientStock i WHERE i.ingredientId = :ingredientId")
    IngredientStock findBoxConcentrateByProductId(String ingredientId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.ingredientId = :ingredientId")
    int findBoxNumByProductId(@Param("ingredientId") String ingredientId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.ingredientId = :ingredientId")
    int findPouchNumByProductId(@Param("ingredientId") String ingredientId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.ingredientId = :ingredientId")
    int findStickPouchNumByProductId(@Param("ingredientId") String ingredientId);

    @Query("SELECT i.quantity FROM IngredientStock i WHERE i.ingredientId = :ingredientId")
    int findCollagenNumByProductId(@Param("ingredientId") String ingredientId);


    Optional<IngredientStock> findByIngredientId(String ingredientId);
}
