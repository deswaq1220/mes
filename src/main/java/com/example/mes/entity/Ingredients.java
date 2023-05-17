package com.example.mes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredients {

    @Id
    @Column(length = 20, nullable = false)
    private String ingredient_id;

    @Column(nullable = false)
    private String ingredient_name;

    @Column(nullable = false)
    private String product_id;
}
