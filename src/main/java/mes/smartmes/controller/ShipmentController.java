package mes.smartmes.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.Shipment;
import mes.smartmes.repository.ShipmentRepository;
import mes.smartmes.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentRepository shipmentRepository;




    //출하 등록
    @GetMapping("/shipment")
    public String save(){
        String dayNo = "SD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int shipmentIntNo;

        if (shipmentService.selectShipmentNo() == null) {
            shipmentIntNo = 1;
        } else {
            shipmentIntNo = Integer.parseInt(shipmentService.selectShipmentNo()) + 1;
        }


        System.out.println("=========================");
        System.out.println((shipmentIntNo));
        //System.out.println(dayNo + String.format("%04d", porderIntNo));
        //pdto.setPorderNo(dayNo + String.format("%04d", porderIntNo));

        String orderNo = dayNo + String.format("%04d", shipmentIntNo);
        System.out.println(orderNo);
        return "shipment";

    }

    //출하 등록
    @PostMapping("/addShipment")
    // 저장할 모델 객체, 보내줄 디비 객체
    public String saveShipment(Shipment shipment, Model model){
        shipment.setShipmentDate(LocalDateTime.now());
        String dayNo = "SD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int shipmentIntNo=0;

        // 값이 없을 시 값 시작 값 생성
        if (shipmentService.selectShipmentNo() == null) {
            shipmentIntNo = 1;
            String shipmentNo = dayNo + String.format("%04d", shipmentIntNo);
            shipment.setShipmentNo(shipmentNo);
            shipment.setShipmentDate(LocalDateTime.now());
            shipmentRepository.save(shipment);
        } else {
            shipmentIntNo = Integer.parseInt(shipmentService.selectShipmentNo()) + 1;
            String shipmentNo = dayNo + String.format("%04d", shipmentIntNo);
            shipment.setShipmentNo(shipmentNo);
            shipment.setShipmentDate(LocalDateTime.now());
            shipmentRepository.save(shipment);
        }
        shipmentRepository.save(shipment);
        System.out.println(shipment);

        return  "shipment";
    }


    // 조회
    @RequestMapping("/shipmentList")
    public String shipmentList(Model model){

        List<Shipment> shipmentList = shipmentRepository.findAll();

        // orderList 리스트 객체를 orderList 라는 이름으로 뷰페이지에서 사용 가능하게 세팅
        model.addAttribute("shipmentList", shipmentList);

        return "shipment";
    }


}
