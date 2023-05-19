package mes.smartmes.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "bom")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BOM {

    @Id
    @Column(length = 20)
    private String bomId;                      //bom id

    private String productId;                  //제품 id

    private long ingredientId;                 //원자재, 부품 id

    private long productQty;                   //생산량

    private long ingreQty;                     //생산량 대비 제품 소요량

    private String ingreQtyUnit;               //소요량 단위


}
