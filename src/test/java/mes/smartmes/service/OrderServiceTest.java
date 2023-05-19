package mes.smartmes.service;

import mes.smartmes.entity.Routing;
import mes.smartmes.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    OrdersService service;

    Routing routing;
    @Test
    public void test(){

        System.out.println(service.selectProcessTime());


    }
}
