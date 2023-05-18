package mes.smartmes.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shipmenttest")
public class shipmenttest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_no")
    private Long shipmentNo; // 출하번호 (Primary Key)

    @Column(name = "shipment_date")
    private LocalDate shipmentDate; // 출하일자


    @Column(name = "company_name")
    private String companyName; // 거래처

    @Column(name = "vat")
    private String vat; // 부가세

    @Column(name = "item_quantity")
    private String itemQuantity; // 품목(수)

    @Column(name = "supply_price")
    private Double supplyPrice; // 공급가

    @Column(name = "vat_amount")
    private Double vatAmount; // 부가세액

    @Column(name = "total_amount")
    private Double totalAmount; // 합계

    @Column(name = "remarks")
    private String remarks; // 비고

    @Column(name = "modification_date")
    private LocalDateTime modificationDate; // 수정일


}
