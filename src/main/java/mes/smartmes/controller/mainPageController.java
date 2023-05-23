//package mes.smartmes.controller;


import mes.smartmes.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

//@Controller
//public class mainPageController {

//    @Autowired
//    private CalendarService calendarService;
//
//    @GetMapping("/index") //첫페이지
//    public String indexpage(){
//        return "index";
//    }
//
//
//    @RequestMapping //기본 페이지 표시
//    public String viewCalendar(){
//        return "currentPage";
//    }
//
//    @GetMapping("/event") //ajax 데이터 전송 URL
//    public @ResponseBody List<Map<String, Object>> getEvent(){
//        return calendarService.getEventList();
//    }
//
//    @GetMapping("/order")
//    public String orderPage(){
//        return "order";
//    }
//
//
//    @GetMapping("/management")
//    public String managementPage(){
//        return "Ordermanagement";
//    }
//
//    @GetMapping("/Porder")
//    public String PorderPage(){
//        return "Porder";
//    }
//
//
//    @GetMapping("/bom")
//    public String bomPage(){
//        return "BOM";
//    }
//
//    @GetMapping("/ingredient")
//    public String ingredientPage(){
//        return "ingredientStock";
//    }

//    @GetMapping("/inout")
//    public String inoutPage(){
//        return "inout";
//    }

//    @GetMapping("/process")
//    public String processPage(){
//        return "process";
//    }
//
//    @GetMapping("/prodperform")
//    public String prodperPage(){
//        return "prodPerform";
//    }
//    @GetMapping("/production")
//    public String productionPage(){
//        return "Production";
//    }
//    @GetMapping("/routing")
//    public String routingPage(){
//        return "Routing";
//    }

//    @GetMapping("/workorder")
//    public String work(){
//        return "workorder";
//    }







//}