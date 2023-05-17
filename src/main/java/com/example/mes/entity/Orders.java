package com.example.mes.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "orders")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Orders {

    @Id
    @Column(length = 20, nullable = false)
    private String order_no;

    @Column(length = 20, nullable = false)
    private String company_id;

    @Column(nullable = false)
    private Date order_date;

    @Column(length = 20, nullable = false)
    private String product_id;

    @Column(nullable = false)
    private int order_quantity;

    private int order_price;

    @Column(nullable = false)
    private Date delivery_date;

    @Column(length = 1)
    private String order_status;
}
