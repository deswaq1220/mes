package Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping("/main")
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    @RequestMapping //기본페이지 표지
    public String viewCalendar(){
        return "/calendarPage"
    }




}
