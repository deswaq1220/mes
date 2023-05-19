package mes.smartmes.repository;

import mes.smartmes.Entity.Shipment;
import mes.smartmes.Entity.shipmenttest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>, QuerydslPredicateExecutor<Shipment> {

    // 데이터베이스에서 모든 shipment 데이터를 조회하는 메서드
    List<Shipment> findAll();


    // 검색

//    List<Shipment> findByFinproductNo(String finproductNo);
//
//    @Query("SELECT s FROM Shipment s WHERE s.company = :company " +
//            "AND s.shipmentDate BETWEEN :startDate AND :endDate " +
//            "AND s.finproductNo = :finproductNo " +
//            "AND s.productId = :product " +
//            "AND s.companyName = :companyName")
//    List<Shipment> searchByConditions(@Param("company") String company,
//                                      @Param("startDate") String startDate,
//                                      @Param("endDate") String endDate,
//                                      @Param("finproductNo") String finproductNo,
//                                      @Param("product") String product,
//                                      @Param("companyName") String companyName);

}
