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
@Table(name="workOrder")
@Getter
@Setter
@ToString
public class WorkOrder {
    @Id
    @Column(length = 20)
    private String workOrderNo;

    private int workOrderSeq;

    private LocalDateTime workOrderDate;

    @Column(length = 20)
    private String prodPlanNo;

    private int prodPlanSeq;

    @Column(length = 20)
    private String productId;
    private String processNo;

    private int orderQuantity;

    @Column(length = 1)
    private String workStatus;

    @Column(length = 20)
    private String equipmentId;

}
