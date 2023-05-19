package mes.smartmes.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "porder")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Porder {      // 발주 테이블

    @Id
    @Column(length = 20)
    private  String porderNo;                       // 발주 번호

    private LocalDateTime porderDate;               // 발주 날짜

    @ColumnDefault("A")
    private char porderStatus;                      // 발주 주문 상태(A:주문 완료, B:진행중, C: 완료)

    @Column(length = 20)
    private String ingredientId;                   // 재료 id

    private int porderQuantity;                     // 주문 수량

    @Column(length = 20)
    private String supplierId;                      // 공급 업체 id

    @ColumnDefault("N")
    private  char emergency_yn;                     // 긴급 요청 여부

}
