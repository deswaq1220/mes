package mes.smartmes.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(value = {AuditingEntityListener.class})
@Entity
@Table(name = "prodplan")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionPlan {

    @Id
    @Column(length = 20)
    private String prodPlanNo;

    private int prodPlanSeq;

    @CreatedDate
    @Column(updatable = false)
    private Date prodPlanDate;

    @Column(length = 20, nullable = false)
    private String productId;

    @Column(nullable = false)
    private int prodPlanQuantity;

    @Column(length = 20)
    private String orderNo;

    @Column(length = 10)
    private String prodPlanFinYn;

    @Column(length = 10)
    private String productName;

}
