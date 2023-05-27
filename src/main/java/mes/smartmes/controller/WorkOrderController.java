package mes.smartmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mes")
public class WorkOrderController {

    @GetMapping("workorder")
    public String test(){

        return "workorder";
    }
}
