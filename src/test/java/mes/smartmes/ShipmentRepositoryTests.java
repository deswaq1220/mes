package mes.smartmes;

import mes.smartmes.entity.Orders;
import mes.smartmes.repository.ShipmentRepository;
import mes.smartmes.service.ShipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ShipmentRepositoryTests {

    @Autowired
    ShipmentRepository shipmentRepository;
    @Autowired
    ShipmentService shipmentService;

    @Test
    void shipmenttest() {
        List<Orders> orders= shipmentService.searchOrders("OD202305250001",null);
        System.out.println("디어니ㅑ렁니랸ㅇㄹ"+orders);

    }


}
