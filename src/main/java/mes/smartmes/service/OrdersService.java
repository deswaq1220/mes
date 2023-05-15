package mes.smartmes.service;

import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import mes.smartmes.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    private OrdersDTO ordersDTO;
    private OrdersRepository ordersRepository;
    private Orders orders;



    public Orders getOrderByOrderNo(String orderNo) {
        return ordersRepository.findByOrderNo(orderNo);
    }

/*    public Orders updateOrderByOrderNo(String orderNo, OrdersDTO ordersDTO) {
    }*/

    public void deleteByOrderNo(String orderNo) {
        ordersRepository.deleteByOrderNo(orderNo);
    }

}
