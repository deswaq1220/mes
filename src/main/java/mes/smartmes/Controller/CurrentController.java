package mes.smartmes.Controller;


import mes.smartmes.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class CurrentController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping //기본 페이지 표시
    public String viewCalendar(){
        return "/currentPage";
    }

    @GetMapping("/event") //ajax 데이터 전송 URL
    public @ResponseBody List<Map<String, Object>> getEvent(){
        return calendarService.getEventList();
    }



}