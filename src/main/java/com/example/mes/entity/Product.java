package com.example.mes.entity;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name ="product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String prodPlan_id;

    @Column(length = 200, nullable = false)
    private String product_name;
}
