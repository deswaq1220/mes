package mes.smartmes.repository;

import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders save(Orders Orders);


    @Query(value = "SELECT MAX(RIGHT(o.order_no,4)) FROM orders AS o WHERE (select date_format(order_date, '%Y%m%d')) = (Select date_format(sysdate(), '%Y%m%d'))",nativeQuery = true)
    String findByOrderNo();

    Optional<Orders> findById(String orderNo);


    Orders findByCompanyId(String companyId);
    int deleteByOrderNo(String orderNo);
    List<Orders> findAll();

//    String selectMaxOrderNo();
//
//    String findMaxOrderNo();

    //리드타임
    @Query(value = "SELECT p.lead_time FROM process p WHERE processno = :processNo" ,nativeQuery = true)
    long findLeadTime(String processNo);

    //생산시간
    @Query(value = "SELECT p.process_time FROM process p WHERE processno = :processNo" ,nativeQuery = true)
    long findProcessTime(String processNo);

    //생산능력(prcess_capacity)
    @Query(value = "SELECT p.process_capacity FROM process p WHERE processno = :processNo" ,nativeQuery = true)
    long findCapa(String processNo);

    @Query(value ="SELECT dayofweek(now())",nativeQuery = true)
    long findWorkDay();

    @Query(value ="SELECT date_format(:totalProcessTime,'%H%i%S')",nativeQuery = true)
    String findWorkTime(LocalDateTime totalProcessTime);


}
