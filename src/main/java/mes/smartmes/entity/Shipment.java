package mes.smartmes.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDate;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipmentManagement")
public class Shipment {

    @Id
    @Column(length = 20)



    private String shipmentNo; // 출하번호

    @Column(length = 20)
    private String productId;    // 제품코드

    @Column(length = 50)
    private String companyName; // 거래처이름

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate shipmentDate; // 출하일자

    private int shipmentQuantity; // 상태

    @Column(length = 20)
    private String shipmentStatus; // 수량

    @OneToOne                        // 수주관리
    @JoinColumn(name = "order_id")
    private Orders order;

}
