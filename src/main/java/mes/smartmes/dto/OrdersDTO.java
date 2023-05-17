package mes.smartmes.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrdersDTO {


    private String orderNo;                 // 수주 번호

    private String companyId;               //  업체 id

    private Date orderDate;                 // 주문날짜

    private String productId;               // (고객 주문) 제품 id

    private int orderQuantity;             // 주문 수량  box 단위

    private int orderPrice;                // 주문 가격

    private Date deliveryDate;              // 납품일자

    private char orderStatus;            // 주문 상태 A = 주문접수 , B =  수주확정

}
