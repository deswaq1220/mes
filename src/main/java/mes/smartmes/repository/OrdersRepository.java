package mes.smartmes.repository;

import mes.smartmes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders save(Orders orders);

    List<Orders> findAll();


    //리드타임
    @Query(value = "SELECT p.lead_time FROM process p WHERE processno = :processno" ,nativeQuery = true)
    long findLeadTime(String processno);

    //생산시간
    @Query(value = "SELECT p.process_time FROM process p WHERE processno = :processno" ,nativeQuery = true)
    long findProcessTime(String processno);

    //생산능력(prcess_capacity)
    @Query(value = "SELECT p.process_capacity FROM process p WHERE processno = :processno" ,nativeQuery = true)
    long findCapa(String processno);

    @Query(value ="SELECT dayofweek(:currentTime)",nativeQuery = true)
    long findWorkDay(LocalDateTime currentTime);

    @Query(value ="SELECT date_format(:totalProcessTime,'%H%i%S')",nativeQuery = true)
    String findWorkTime(LocalDateTime totalProcessTime);














}
