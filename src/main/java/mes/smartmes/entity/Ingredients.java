package mes.smartmes.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //오토 인크리먼트 설정
    private int ingredientId;

    @Column(length = 50)
    private String ingredientName;                          // 재료명

    @Column(length = 20)
    private String productId;                               // 제품 id



}
