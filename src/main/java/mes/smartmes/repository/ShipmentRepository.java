package mes.smartmes.repository;

import mes.smartmes.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {

    Optional<Shipment> findById(String shipmentNo);


    @Query(value = "SELECT MAX(RIGHT(s.shipmentNo,4)) FROM shipment AS s WHERE (select date_format(shipmentDate, '%Y%m%d')) = (Select date_format(sysdate(), '%Y%m%d'))",nativeQuery = true)
    String findByShipmentNo();

    List<Shipment> findAll();


    int deleteByShipmentNo(String shipmentNo);
}
