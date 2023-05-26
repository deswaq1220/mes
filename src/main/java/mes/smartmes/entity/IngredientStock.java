package mes.smartmes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "ingredientStock")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientStock {

    @Id
    @Column(length = 20, nullable = false)
    private String stockNo;

    @Column(length = 20, nullable = false)
    private String productId;

    @Column(length = 20, nullable = false)
    private String ingredientId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Date productDate;


}
