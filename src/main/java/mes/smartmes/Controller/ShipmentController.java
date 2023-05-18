package mes.smartmes.Controller;

import lombok.extern.log4j.Log4j2;
import mes.smartmes.Entity.shipmenttest;
import mes.smartmes.Service.ShipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

//    @RequestMapping
//    public String getAllShipments(Model model) {
//        List<shipmenttest> shipments = shipmentService.getAllShipments();
//        model.addAttribute("shipments", shipments);
//        return "shipment";
//    }

    @RequestMapping
    public String list(Model model){
        List<shipmenttest> shipments = shipmentService.getAllShipments();
        model.addAttribute("shipmentlist",shipments);
        return "shipment";
    }
}