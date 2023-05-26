package mes.smartmes.controller;


import mes.smartmes.entity.Ingredients;
import mes.smartmes.entity.Porder;
import mes.smartmes.repository.PorderRepository;
import mes.smartmes.service.IngredientService;
import mes.smartmes.service.PorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/mes")
public class PorderController {

    private Porder porder;
    private PorderRepository porderRepository;
    @Autowired
    private PorderService porderService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/porder")
    public String save(){
//        porderService.("PD20230522003");

        ingredientService.updatePorderStatusAndInsertIngredient("PD202305260009");
        ingredientService.updatePorderStatusAndInsertIngredient("PD202305260010");
        ingredientService.updatePorderStatusAndInsertIngredient("PD202305260011");
        ingredientService.updatePorderStatusAndInsertIngredient("PD202305260012");
        System.out.println("===========================");
        System.out.println("===========================");
        System.out.println("===========================");
        System.out.println("===========================");
        System.out.println("===========================");








        return "Porder";
    }




}
