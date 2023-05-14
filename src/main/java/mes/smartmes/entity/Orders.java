package mes.smartmes.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.text.DateFormat;


@Getter @Setter @ToString
@Entity @Builder
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {


    @Id
    private String orderNo;                 // 수주 번호

    private String companyId;               //  업체 id

    private Date orderDate;                 // 주문날짜

//    private String productId;             // product Entity 만들어 지면 반영

    private Long orderQuantity;             // 주문 수량  box 단위

    private Long orderPrice;                // 주문 가격

    private Date deliveryDate;              // 납품일자

    private boolean orderStatus;            // 주문 상태 true = 주문접수 , false 수주확정


}
