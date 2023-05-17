package com.example.mes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "porder")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Porder {

    @Id
    @Column(length = 20, nullable = false)
    private String porder_no;

    @Column(nullable = false)
    private Date porder_date;

    @Column(length = 1)
    private String porder_status;

    @Column(length = 20, nullable = false)
    private String ingredient_id;

    private int porder_quantity;

    @Column(length = 20)
    private String supplier_id;

    @Column(length = 1)
    private String emergency_yn;


}
