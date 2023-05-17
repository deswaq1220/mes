package com.example.mes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "prodPlan")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionPlan {

    @Id
    @Column(length = 20)
    private String prodPlan_no;

    private int prodPlan_seq;

    private Date prodPlan_date;

    @Column(length = 20, nullable = false)
    private String product_id;

    @Column(nullable = false)
    private int prodPlan_quantity;

    @Column(length = 20)
    private String order_no;

    @Column(length = 5)
    private String prodPlan_fin_yn;

}
