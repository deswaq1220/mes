package mes.smartmes.Controller;


import mes.smartmes.Entity.Shipment;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;

    public ShipmentController(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @GetMapping("/shipments")
    public String getAllShipments(Model model) {
        List<Shipment> shipments = createDummyShipments(); // 더미 데이터 생성
        model.addAttribute("shipments", shipments);
        return "shipment"; // 뷰의 이름 (예: shipment-list.html)
    }

    private List<Shipment> createDummyShipments() {
        List<Shipment> shipments = new ArrayList<>();

        // 더미 데이터 생성 및 추가
        Shipment shipment1 = new Shipment();
        shipment1.setShipmentNo(1L);
        shipment1.setFinproductNo("FP001");
        shipment1.setCompanyName("쿠팡");
        shipment1.setShipmentDate(LocalDate.now());
        shipment1.setProductId(100L);
        shipment1.setShipmentQuantity(10);
        shipment1.setShipmentStatus("Pending");
//        shipment1.setVat(new BigDecimal("100.00"));
//        shipment1.setModificationDate(LocalDateTime.now());
        shipments.add(shipment1);

        Shipment shipment2 = new Shipment();
        shipment2.setShipmentNo(2L);
        shipment2.setFinproductNo("FP002");
        shipment2.setCompanyName("지마켓");
        shipment2.setShipmentDate(LocalDate.now());
        shipment2.setProductId(200L);
        shipment2.setShipmentQuantity(20);
        shipment2.setShipmentStatus("Completed");
//        shipment2.setVat(new BigDecimal("200.00"));
//        shipment2.setModificationDate(LocalDateTime.now());
        shipments.add(shipment2);

        return shipments;
    }
}