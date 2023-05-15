    package mes.smartmes.controller;

    import mes.smartmes.dto.OrdersDTO;
    import mes.smartmes.entity.Orders;
    import mes.smartmes.repository.OrdersRepository;
    import mes.smartmes.service.OrdersService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    @Controller
    @RequestMapping("/mes/orders")
    public class OrdersController {
        private OrdersRepository ordersRepository;
        private OrdersService ordersService;

        public OrdersController(OrdersRepository ordersRepository, OrdersService ordersService) {
            this.ordersRepository = ordersRepository;
            this.ordersService = ordersService;
        }


        @GetMapping("/test")
        public String save(){
            return "test";
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

        @DeleteMapping("/{orderNo}")
        public ResponseEntity<?> deleteOrder(@PathVariable String orderNo) {
            ordersRepository.deleteByOrderNo(orderNo);
            return ResponseEntity.ok().build();
        }
    }

