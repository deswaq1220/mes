package mes.smartmes.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
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
    @Column(length = 20)
    private String orderNo;                 // 수주 번호

    @Column(length = 20)
    private String companyId;               //  업체 id

    @Column(length = 20)
    private Date orderDate;                 // 주문날짜

    @Column(length = 20)
    private String productId;               // (고객 주문) 제품 id

    private int orderQuantity;             // 주문 수량  box 단위

    private int orderPrice;                // 주문 가격

    private Date deliveryDate;              // 납품일자

    private char orderStatus;            // 주문 상태 A = 주문접수 , B =  수주확정


}
