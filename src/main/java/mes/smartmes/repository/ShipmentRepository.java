package mes.smartmes.repository;

import mes.smartmes.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>, QuerydslPredicateExecutor<Shipment> {

    // 데이터베이스에서 모든 shipment 데이터를 조회하는 메서드
    List<Shipment> findAll();



}
