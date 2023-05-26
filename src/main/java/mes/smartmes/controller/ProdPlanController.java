package mes.smartmes.controller;

import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Product;
import mes.smartmes.entity.ProductionPlan;

import mes.smartmes.repository.ProdPlanRepository;
import mes.smartmes.service.ProdPlanService;
import mes.smartmes.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/mes")
public class ProdPlanController {

    private final ProdPlanService prodplanservice;
    private final ProductService productservice;
    private final ProdPlanRepository prodplanrepository;

    @GetMapping("/prodPlan")
    public String selectList(Model model) {

        List<ProductionPlan> prodList = prodplanservice.selectList();
        List<Product> productList = productservice.selectList();
        model.addAttribute("prodPlans", prodList);
        model.addAttribute("products", productList);


        Pageable pageable = PageRequest.of(0,10);
        Page<ProductionPlan> result = prodplanrepository.findAll(pageable);

        System.out.println("===================");
        System.out.println(result);
        System.out.println("Total Pages:"+result.getTotalPages());
        return "Production";
    }


    @GetMapping("/search")
    public String searchForm() {
        return "Production";
    }

    @PostMapping("/search")
    public String performSearch(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                @RequestParam("status") String prodPlanFinYn,
                                @RequestParam("items") String productName,
                                Model model) {
        System.out.println(startDate.toString());

        List<ProductionPlan> searchResults = prodplanservice.findSearch(startDate, endDate, prodPlanFinYn, productName);
        model.addAttribute("prodPlans", searchResults);


        return "Production"; // 검색 결과를 표시하는 뷰로 반환
    }
}

