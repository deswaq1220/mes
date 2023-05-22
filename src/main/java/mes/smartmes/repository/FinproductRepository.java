package mes.smartmes.repository;


import mes.smartmes.entity.Finproduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FinproductRepository extends JpaRepository<Finproduct, String> {

    @Query("SELECT f FROM Finproduct f WHERE f.product_id = :productId")
    Finproduct findByProductId(String productId);


    @Modifying
    @Transactional
    @Query("UPDATE Finproduct fi SET fi.finProduct_quantity = fi.finProduct_quantity - :finProductQuantity WHERE fi.product_id = :productId")
    void decreaseStockQuantity(@Param("productId") String productId, @Param("finProductQuantity") int finProductQuantity);
}
