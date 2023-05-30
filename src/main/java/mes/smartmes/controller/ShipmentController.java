package mes.smartmes.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.Porder;
import mes.smartmes.entity.Product;
import mes.smartmes.entity.Shipment;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.PorderRepository;
import mes.smartmes.repository.ProductRepository;
import mes.smartmes.repository.ShipmentRepository;
import mes.smartmes.service.CalendarService;
import mes.smartmes.service.OrdersService;
import mes.smartmes.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
@Log4j2
@Getter
@Setter
@RequestMapping("/mes")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentRepository shipmentRepository;

    private OrdersService ordersService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PorderRepository porderRepository;
    @Autowired
    private CalendarService calendarService;


    public ShipmentController(ShipmentService shipmentService, OrdersService ordersService) {
        this.shipmentService = shipmentService;
        this.ordersService = ordersService;
    }



    @GetMapping("/event") //ajax 데이터 전송 URL
    public @ResponseBody List<Map<String, Object>> getEvent(){
        return CalendarService.getEventList();
    }



    // 디비에 있는 데이터  뷰페이지에 전달
    @GetMapping("/shipment")
    public String list(Model model){
        List<Shipment> shipments = shipmentService.getAllShipments();
        List<Orders>  orderList = ordersRepository.findAll();
        List<Product> productList = productRepository.findAll();
        List<Porder> porderList = porderRepository.findAll();
        model.addAttribute("shipmentlist",shipments);
        model.addAttribute("orderList",orderList);
        model.addAttribute("productList",productList);
        model.addAttribute("porderList",porderList);
        return "shipment";
    }

    //다중검색

    @GetMapping("/shipmentsearch")
    public String search(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
                         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate,
                         @RequestParam(name = "shipmentNo") String shipmentNo,
                         @RequestParam(name = "companyName") String companyName,
                         @RequestParam(name = "orderNo") String orderNo,
                         @RequestParam(name = "productId") String productId,

                         Model model) {

        // 여기에서 검색 로직을 수행하고, 결과를 모델에 저장합니다.
        // 예시로서 각 매개변수를 모델에 추가하고 "searchResults"라는 이름으로 반환합니다.
        List<Shipment> shipments = shipmentService.searchShipment(shipmentNo,startDate,endDate,companyName);
        List<Orders> orders = shipmentService.searchOrders(orderNo,productId);
        System.out.println(shipments);
        model.addAttribute("shipmentlist",shipments);
        model.addAttribute("orderList", orders);

        return "shipment";
    }


}
