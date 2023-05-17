package mes.smartmes.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import mes.smartmes.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;


    //조회
    public String selectOrderNo(){
        String OrderIntNo = ordersRepository.findByOrderNo();
        return  OrderIntNo;
    }


/*    public Orders updateOrderByOrderNo(String orderNo, OrdersDTO ordersDTO) {
    }*/


    // 삭제
    public int deleteByOrderNo(String orderNo) {
        return ordersRepository.deleteByOrderNo(orderNo);
    }

    //




//    public String selectMaxOrderNo() {
//        return ordersRepository.selectMaxOrderNo();
//    }

/*    public void saveOrder() {
        Orders orders = new Orders();
        String orderNoPrefix = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String orderNo;

        String maxOrderNo = selectMaxOrderNo();

        if (maxOrderNo == null) {
            orderNo = orderNoPrefix + "001";
            orders.setOrderNo(orderNo);
            ordersRepository.save(orders);
        } else {
            int serialNumber = Integer.parseInt(maxOrderNo.substring(maxOrderNo.length() - 3));
            serialNumber++;
            orderNo = String.format("%03d", serialNumber);
            orderNo = orderNoPrefix + orderNo;
            orders.setOrderNo(orderNo);
            ordersRepository.save(orders);
        }

        System.out.println(orders);
    }*/
}
