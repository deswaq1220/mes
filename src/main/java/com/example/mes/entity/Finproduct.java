package com.example.mes.entity;

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
    @Column(length = 20, nullable = false)
    private String finProduct_no;

    @Column(length = 20, nullable = false)
    private String product_id;

    @Column(nullable = false)
    private int finProduct_quantity;

    @Column(nullable = false)
    private Date product_date;

}
