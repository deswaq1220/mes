package mes.smartmes.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Ingredients;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.Product;
import mes.smartmes.repository.IngredientsRepository;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.ProcessRepository;
import mes.smartmes.repository.ProductRepository;
import mes.smartmes.service.LotService;
import mes.smartmes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private LotService lotService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/main")
    public String main(){
        return "main";
    }


    // 수주 화면에 수주목록 띄우기
    @GetMapping("/order")
    public String save(Model model) {
        List<Orders>  orderList = ordersRepository.findAll();
        List<Product> productList = productRepository.findAll();

        // order에 수주 상세 표시
        model.addAttribute("orderList", orderList);
        model.addAttribute("productList", productList);

        return "order";
    }

    //삭제
    @GetMapping("/deleteOrder/{orderNo}")
    public String  deleteOrder(@PathVariable String orderNo) {
        ordersService.deleteByOrderNo(orderNo);

        return "redirect:/mes/order";
    }



    //수주 등록 후 오더페이지로
    @PostMapping("/addOrder")
    @ResponseStatus(value= HttpStatus.OK)
    public void saveOder(Orders orders, @RequestParam("orderDateStr")String orderDateStr , Model model, HttpServletRequest request){
        System.out.println("=========================");

        //orders.setOrderDate(LocalDateTime.now());
        String dayNo = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int orderIntNo=0;
        String orderNo;

        // 값이 없을 시 값 시작 값 생성
        if (ordersService.selectOrderNo() == null) {
            orderIntNo = 1;
            orderNo = dayNo + String.format("%04d", orderIntNo);
        } else {
            orderIntNo = Integer.parseInt(ordersService.selectOrderNo()) + 1;
            orderNo = dayNo + String.format("%04d", orderIntNo);
        }
        System.out.println(orderNo);


        orders.setOrderNo(orderNo);
        orders.setOrderDate(LocalDate.parse(orderDateStr).atStartOfDay());
        orders.setCompanyId(request.getParameter("companyId"));
        orders.setProductId(request.getParameter("productId"));
        orders.setOrderQuantity(Integer.parseInt(request.getParameter("orderQty")));
        orders.setOrderStatus("A");

        ordersRepository.save(orders);
        System.out.println(orders);
//        ordersService.selectProcessTime();

    }
    // 조회
    @GetMapping("orderList")
    public String orderList(Model model){

        List<Orders>  orderList = ordersRepository.findAll();

        // orderList 리스트 객체를 orderList 라는 이름으로 뷰페이지에서 사용 가능하게 세팅
        model.addAttribute("orderList", orderList);

        return "order";
    }

    //주문 수정 페이지
    @GetMapping("/orderUpdate/{orderNo}")
    public String updateOrderDetail(@PathVariable("orderNo") String orderNo, Model model ){
        // 오더
        Orders orderView = new Orders();
        model.addAttribute("orders", orderView);
        Orders orders = ordersRepository.findById(orderNo).get();
        System.out.println(orders);

        // 뷰단에 넘겨줄 때 이름 체크
        model.addAttribute("orderList", orders);

        return "order";
    }
    
    //수정 후 저장
    @PostMapping("/orderUpdate")
    public String  updateOrderPage(Orders orders, BindingResult result) {

        ordersRepository.save(orders);
        return "redirect:/mes/orderList";
    }


    @GetMapping("/mainOrder")
    public String mainSave(){
        return "order";
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


