package mes.smartmes.repository;

import mes.smartmes.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {
    Orders save(Orders orders);
    Orders findByOrderNo(String orderNo);
    Orders findByCompanyId(String companyId);
    Integer deleteByOrderNo(String orderNo);
    List<Orders> findAll();


}
