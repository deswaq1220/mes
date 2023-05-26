package mes.smartmes.entity;

import lombok.*;
import mes.smartmes.dto.OrdersDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "orders")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Orders {


    @Id
    @Column(length = 20)
    private String orderNo;                 // 수주 번호

    @Column(length = 20)
    private String companyId;               //  업체 id

    private LocalDate orderDate;                 // 주문날짜

    @Column(length = 20)
    private String productId;               // (고객 주문) 제품 id

    private int orderQuantity;             // 주문 수량  box 단위

    private int orderPrice;                // 주문 가격

    // 스프링이 시간을 적용 되게 해주는 어노테이션
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime deliveryDate;              // 납품일자

    @Column(length = 1)
    private String orderStatus;            // 주문 상태 A = 주문접수 , B =  수주확정, C = 생산계획 완료


<<<<<<< Updated upstream
=======
   // @OneToOne(mappedBy = "order")  // 출하관리 좀 가지고 올게경..
   // private Shipment shipment;



>>>>>>> Stashed changes

 /*   // Orders -> OrdersDTO
    public OrdersDTO toOrderDTO(Orders orders) {
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrderNo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        ordersDTO.setCompanyId(orders.getCompanyId());
        ordersDTO.setProductId(orders.getProductId());
        ordersDTO.setOrderQuantity(orders.getOrderQuantity());
        ordersDTO.setOrderPrice(orders.getOrderPrice());
        ordersDTO.setDeliveryDate(orders.getDeliveryDate());
        ordersDTO.setOrderStatus(orders.getOrderStatus());
        return ordersDTO;
    }
*/


}
