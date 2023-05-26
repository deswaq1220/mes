package mes.smartmes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @Column(nullable = false)
    private int ingredientNo;

    @Column(length = 20, nullable = false)
    private String ingredientId;

    @Column(nullable = false)
    private String ingredientName;

    @Column(nullable = false)
    private String productId;
}
