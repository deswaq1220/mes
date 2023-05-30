package mes.smartmes.controller;


import mes.smartmes.entity.Porder;
import mes.smartmes.repository.PorderRepository;
import mes.smartmes.service.IngredientService;
import mes.smartmes.service.PorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/mes")
public class PorderController {

    private Porder porder;
    private PorderRepository porderRepository;
    @Autowired
    private PorderService porderService;

    @Autowired
    private IngredientService ingredientService;

    private Date convertToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }



    @GetMapping("/Porder")
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

    @GetMapping("/porderSearch")
    public String searchForm(){
        return "Porder";
    }

    @PostMapping("/porderSearch")
    public String performSearch(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                @RequestParam("porderStatus") String porderStatus,
                                @RequestParam("supplierId") String supplierId,
                                Model model) {
        Date convertedStartDate = convertToDate(startDate);
        Date convertedEndDate = convertToDate(endDate);
        List<Porder> searchResults = porderService.findSearch(convertedStartDate, convertedEndDate, porderStatus, supplierId);
        model.addAttribute("porders", searchResults);
        return "Porder";
    }




}
