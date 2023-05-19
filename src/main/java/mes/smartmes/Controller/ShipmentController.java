package mes.smartmes.Controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import mes.smartmes.Entity.Shipment;
import mes.smartmes.Entity.shipmenttest;
import mes.smartmes.Service.ShipmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@Getter
@Setter
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

    // 디비에 있는 데이터  뷰페이지에 전달
    @RequestMapping
    public String list(Model model){
        List<Shipment> shipments = shipmentService.getAllShipments();
        model.addAttribute("shipmentlist",shipments);
        return "shipment";
    }

    //다중검색

    @GetMapping("/search")
    public String search(@RequestParam(name = "company") String company,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                         @RequestParam(name = "finproductNo") String finproductNo,
                         @RequestParam(name = "item") String item,
                         @RequestParam(name = "companyName") String companyName,
                         Model model) {

        // 여기에서 검색 로직을 수행하고, 결과를 모델에 저장합니다.
        // 예시로서 각 매개변수를 모델에 추가하고 "searchResults"라는 이름으로 반환합니다.
        List<Shipment> shipments = shipmentService.searchShipment(company,finproductNo,startDate,endDate,companyName,item);
        System.out.println(shipments);
        model.addAttribute("shipmentlist",shipments);

        return "shipment";
    }

}