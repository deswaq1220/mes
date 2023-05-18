package mes.smartmes.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_no")
    private Long shipmentNo; // 출하관리번호 (Primary Key)

    @Column(name = "finproduct_no")
    private String finproductNo; // 완제품번호 (Foreign Key)

    @Column(name = "company_name")
    private String companyName; // 출하업체이름

    @Column(name = "shipment_date")
    private LocalDate shipmentDate; // 출하일

    @Column(name = "product_id")
    private Long productId; // 출하제품아이디

    @Column(name = "shipment_quantity")
    private int shipmentQuantity; // 출하수량

    @Column(name = "shipment_status")
    private String shipmentStatus; // 출하상태





   // 생성자, 게터 및 세터 생략
    
    
    

}