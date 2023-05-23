package mes.smartmes;

import mes.smartmes.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShipmentRepositoryTests {

    @Autowired
    ShipmentRepository shipmentRepository;
//    @Test
//    void shipmenttest() {
//        List<Shipment> shipments = shipmentRepository.findByFinproductNo("sm2305040002");
//        for(int i = 0; i < shipments.size(); i++){
//            System.out.println(shipments.get(i));
//        }
//
//    }
}
