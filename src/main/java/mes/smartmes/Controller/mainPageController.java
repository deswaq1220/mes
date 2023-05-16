package mes.smartmes.Controller;


import mes.smartmes.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class mainPageController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/index") //첫페이지
    public String indexpage(){
        return "index";
    }


    @RequestMapping //기본 페이지 표시
    public String viewCalendar(){
        return "currentPage";
    }

    @GetMapping("/event") //ajax 데이터 전송 URL
    public @ResponseBody List<Map<String, Object>> getEvent(){
        return calendarService.getEventList();
    }

    @GetMapping("/order")
    public String orderPage(){
        return "order";
    }


}