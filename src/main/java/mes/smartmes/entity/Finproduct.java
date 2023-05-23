package mes.smartmes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "finproduct")
public class Finproduct {

    @Id
    @Column(length = 20)
    private String finProductNo;

    @Column(length = 20)
    private String productId;

//    @Column(nullable = false)
    private int finProductQuantity;

//    @Column(nullable = false)
    private Date productDate;

}
