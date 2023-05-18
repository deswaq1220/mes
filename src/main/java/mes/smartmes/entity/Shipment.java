package mes.smartmes.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name ="shipment")
@Getter @Setter @ToString
public class Shipment {

    @Id
    @Column(length = 20)
    private String shipmentNo;              // 출하 관리 번호

    @Column(length = 20)
    private String productId;               // 출하 제품 id

    @Column(length = 50)
    private String companyName;             // 출하 업체 이름

    private LocalDateTime shipmentDate;     // 출하 일

    private int shipmentQuantity;           // 출하수량

    private char shipmentStatus;            // 출하 상태




}
