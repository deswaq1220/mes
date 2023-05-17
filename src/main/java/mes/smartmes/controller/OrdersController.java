package mes.smartmes.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.service.OrdersService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/mes")
public class OrdersController {


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/main")
    public String main(){
        return "main";
    }


    @GetMapping("/order")
    public String save() {
        //수주 번호 생성
        String dayNo = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int orderIntNo;

        if (ordersService.selectOrderNo() == null) {
            orderIntNo = 1;
        } else {
            orderIntNo = Integer.parseInt(ordersService.selectOrderNo()) + 1;
        }


        System.out.println("=========================");
        System.out.println((orderIntNo));
        //System.out.println(dayNo + String.format("%04d", porderIntNo));
        //pdto.setPorderNo(dayNo + String.format("%04d", porderIntNo));

        String orderNo = dayNo + String.format("%04d", orderIntNo);
        System.out.println(orderNo);
        return "orders";
    }

    //수주 등록 
    @PostMapping("/addOrder")
    public String saveOder(Orders orders, Model model){
        orders.setOrderDate(LocalDateTime.now());
        String dayNo = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int orderIntNo=0;
        
        // 값이 없을 시 값 시작 값 생성
        if (ordersService.selectOrderNo() == null) {
            orderIntNo = 1;
            String orderNo = dayNo + String.format("%04d", orderIntNo);
            orders.setOrderNo(orderNo);
            orders.setOrderDate(LocalDateTime.now());
            ordersRepository.save(orders);
        } else {
            orderIntNo = Integer.parseInt(ordersService.selectOrderNo()) + 1;
            String orderNo = dayNo + String.format("%04d", orderIntNo);
            orders.setOrderNo(orderNo);
            orders.setOrderDate(LocalDateTime.now());
            ordersRepository.save(orders);
        }
        ordersRepository.save(orders);
        System.out.println(orders);

        return  "orders";
    }

    // 조회
    @GetMapping("orderList")
    public String orderList(Model model){

        List<Orders>  orderList = ordersRepository.findAll();

        // orderList 리스트 객체를 orderList 라는 이름으로 뷰페이지에서 사용 가능하게 세팅
        model.addAttribute("orderList", orderList);

        return "orders";
    }


    //삭제
    @GetMapping("/deleteOrder/{orderNo}")
    public String  deleteOrder(@PathVariable String orderNo) {
        ordersService.deleteByOrderNo(orderNo);

        return "redirect:/mes/orderList";
    }

    //주문 수정 페이지
    @GetMapping("/orderUpdate/{orderNo}")
    public String updateOrderDetail(@PathVariable("orderNo") String orderNo, Model model ){
        // 오더
        Orders orderView = new Orders();
        model.addAttribute("orders", orderView);
        Orders orders = ordersRepository.findById(orderNo).get();
        System.out.println(orders);

        model.addAttribute("orders", orders);

        return "orderUpdate";
    }
    
    //수정 후 저장
    @PostMapping("/orderUpdate")
    public String  updateOrderPage(Orders orders, BindingResult result) {

        ordersRepository.save(orders);
        return "redirect:/mes/orderList";
    }



}











/*        @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrdersDTO ordersDTO) {
        Orders orders = ordersService.createOrder(ordersDTO);
        return ResponseEntity.ok(orders);
    }*/

/*      @GetMapping("/{orderNo}")
    public ResponseEntity<?> getOrder(@PathVariable String orderNo) {
        Orders orders = ordersService.getOrderByOrderNo(orderNo);
        return ResponseEntity.ok(orders);
    }*/

/*        @PutMapping("/{orderNo}")
    public ResponseEntity<?> updateOrder(@PathVariable String orderNo, @RequestBody OrdersDTO ordersDTO) {
        Orders orders = ordersService.updateOrderByOrderNo(orderNo, ordersDTO);
        return ResponseEntity.ok(orders);
    }*/




/*          데이터 저장 확인 코드
    //수주
    @RequestMapping("/addOrder")
    public String save(*/
/*OrdersDTO ordersDTO,*//*
 Model model) {
        Orders orders = new Orders();
        //수주 번호 생성
        String dayNo = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int orderIntNo=0;

        if (ordersService.selectOrderNo() == null) {
            orderIntNo = 1;
            String orderNo = dayNo + String.format("%04d", orderIntNo);
            orders.setOrderNo(orderNo);
            orders.setOrderDate(LocalDateTime.now());
            ordersRepository.save(orders);
        } else {
            orderIntNo = Integer.parseInt(ordersService.selectOrderNo()) + 1;
            String orderNo = dayNo + String.format("%04d", orderIntNo);
            orders.setOrderNo(orderNo);
            orders.setOrderDate(LocalDateTime.now());
            ordersRepository.save(orders);
        }


        System.out.println(orders);
        System.out.println("=========================");
        System.out.println((orderIntNo));


        return "order";
    }
*/


