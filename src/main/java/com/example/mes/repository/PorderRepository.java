package com.example.mes.repository;

import com.example.mes.entity.Porder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PorderRepository extends JpaRepository<Porder, String> {

    @Query("SELECT p FROM Porder p WHERE p.ingredient_id = :ingredientId")
    List<Porder> findByIngredientId(String ingredientId);

}
