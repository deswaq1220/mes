package mes.smartmes.repository;

import mes.smartmes.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {

    Optional<Shipment> findById(String shipmentNo);


    @Query(value = "SELECT MAX(RIGHT(s.shipment_no,4)) FROM shipment AS s WHERE (select date_format(shipment_date, '%Y%m%d')) = (Select date_format(sysdate(), '%Y%m%d'))",nativeQuery = true)
    String findByShipmentNo();

    List<Shipment> findAll();


    int deleteByShipmentNo(String shipmentNo);
}
