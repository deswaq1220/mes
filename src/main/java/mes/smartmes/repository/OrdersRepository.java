package mes.smartmes.repository;

import mes.smartmes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders save(Orders orders);
    Orders findByOrderNo(String orderNo);
    Orders findByCompanyId(String companyId);
    int deleteByOrderNo(String orderNo);
    List<Orders> findAll();


    @Query(value = "SELECT RIGHT(MAX(o.order_no), 4) FROM order o",nativeQuery = true)
    String findByOrderNo();

    String findMaxOrderNo();
}
