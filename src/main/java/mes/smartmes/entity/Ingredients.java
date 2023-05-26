package mes.smartmes.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ingredientNo;

    @Column(length = 20, nullable = false)
    private String ingredientId;

    @Column(nullable = false)
    private String ingredientName;

    @Column(nullable = false)
    private String productId;

    @Column(name = "input_date")
    private LocalDate inputDate;

}
