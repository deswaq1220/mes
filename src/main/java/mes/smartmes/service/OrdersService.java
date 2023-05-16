package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import mes.smartmes.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {
    private OrdersDTO ordersDTO;


    private final OrdersRepository ordersRepository;
    private final Orders orders;



    public Orders findByOrderNo(String orderNo) {
        return ordersRepository.findByOrderNo(orderNo);
    }

/*    public Orders updateOrderByOrderNo(String orderNo, OrdersDTO ordersDTO) {
    }*/

    public int deleteByOrderNo(String orderNo) {

        return ordersRepository.deleteByOrderNo(orderNo);

    }

}
