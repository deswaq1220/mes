package mes.smartmes.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.LotDTO;
import mes.smartmes.dto.Ratio;
import mes.smartmes.repository.LotRepository;
import mes.smartmes.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@AllArgsConstructor
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private LotRepository lotRepository;

    //@GetMapping("test")
    public String insertLot(LotDTO dto, Model model){

        return "test";
    }


}
