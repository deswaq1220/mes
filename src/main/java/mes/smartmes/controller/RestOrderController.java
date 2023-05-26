package mes.smartmes.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class RestOrderController {

    @Autowired
    private OrdersRepository ordersRepository;

    @PostMapping("/mes/updateStatus")
    @ResponseStatus(value= HttpStatus.OK)
    public void updateStatus(String order_no){
        System.out.println(order_no+"======");
        System.out.println("=====================");
        System.out.println("=====================");
        System.out.println("=====================");
        ordersRepository.updateOrderStatus(order_no);
    }



}
