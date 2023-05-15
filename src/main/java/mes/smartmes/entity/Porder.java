package mes.smartmes.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "porder")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Porder {      // 발주 테이블

    @Id
    @Column(length = 20)
    private  String porderNo;                       // 발주 번호

    private LocalDateTime porderDate;               // 발주 날짜


    private char porderStatus;                      // 발주 주문 상태

    @Column(length = 20)
    private String ingredientsId;                   // 재료 id

    private int porderQuantity;                     // 주문 수량

    @Column(length = 20)
    private String supplierId;                      // 공급 업체 id

    private  char emergency_yn;                     // 긴급 요청 여부





}
