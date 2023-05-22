package mes.smartmes.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PorderController {

    @GetMapping("/porder")
    public String save(){
        return "Porder";
    }



}
