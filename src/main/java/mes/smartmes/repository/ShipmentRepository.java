package mes.smartmes.repository;

import mes.smartmes.Entity.shipmenttest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<shipmenttest, Long> {
    // 데이터베이스에서 모든 shipmenttest 데이터를 조회하는 메서드
    List<shipmenttest> findAll();
}
