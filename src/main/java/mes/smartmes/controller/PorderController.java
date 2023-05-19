package mes.smartmes.controller;

import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.PorderDTO;
import mes.smartmes.entity.Ingredients;
import mes.smartmes.entity.Porder;
import mes.smartmes.service.PorderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@Transactional
@RequiredArgsConstructor
public class PorderController {

    private final PorderService service;
    Porder po;
    Ingredients ingredients;

    //발주 넣기
    @GetMapping("/porder")
    public String putPorder(PorderDTO pdto, Model model){

        //발주 리스트
        model.addAttribute("list", service.selectPorderList());

        //발주 번호 생성
        String dayNo = "PD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int porderIntNo;

       if(service.selectPorderNo() == null){
            porderIntNo = 1;
        }else{
            porderIntNo = Integer.parseInt(service.selectPorderNo())+1;
        }
        System.out.println("=========================");

        String porderNo = dayNo + String.format("%04d", Integer.parseInt(service.selectPorderNo()));
        System.out.println(porderIntNo);
        System.out.println(porderNo);

       //입고 예정 일자 생성
        int inputTime = service.selectTime(porderNo); //시간
        int inputDay = service.selectDay(porderNo);   //요일
        LocalDateTime inputDate = service.selectDate(porderNo);  //실제발주날짜
        LocalDateTime inputIngreDate = inputDate; //입고날짜

        //12시 이후 주문
        if(inputTime > 120000) {
            inputDate = inputDate.plusDays(1);
            inputDay = inputDay+1;
        }

        String emerYn = service.selectEmergencyYn(porderNo);
        System.out.println(emerYn);


        int ingre=5;

        if(ingre == 1 || ingre == 2 || ingre == 6 || ingre == 7 || ingre == 8){
            System.out.println("11111111111111111111111111");
            if(emerYn.equals("N")){
                if(inputDay + 2 == 2 || inputDay + 2 == 4 || inputDay + 2 == 6){
                    inputIngreDate = inputDate.plusDays(2).withHour(10).withMinute(0).withSecond(0) ;
                }else if(inputDay + 2 == 1 || inputDay + 2 == 3 || inputDay + 2 == 5){
                    inputIngreDate = inputDate.plusDays(3).withHour(10).withMinute(0).withSecond(0);
                }else if(inputDay + 2 == 7){
                    inputIngreDate = inputDate.plusDays(4).withHour(10).withMinute(0).withSecond(0);
                }
            }else {
                inputIngreDate = inputDate;
            }
        }else if(ingre == 3 || ingre == 4 || ingre == 5){
            System.out.println("222222222222222222222");
            System.out.println(inputDay);
            if(emerYn.equals("N")){
                if(inputDay + 3 == 2 || inputDay + 3 == 4 || inputDay + 3 == 6){
                    inputIngreDate = inputDate.plusDays(3).withHour(10).withMinute(0).withSecond(0) ;
                }else if(inputDay + 3 == 1 || inputDay + 3 == 3 || inputDay + 3 == 5){
                    inputIngreDate = inputDate.plusDays(4).withHour(10).withMinute(0).withSecond(0);
                }else if(inputDay + 3 == 7){
                    inputIngreDate = inputDate.plusDays(5).withHour(10).withMinute(0).withSecond(0);
                }
            }else {
                inputIngreDate = inputDate;
            }
        }


        System.out.println("발주날짜 = " + inputDate); //발주날짜
        System.out.println("입고날짜 = " + inputIngreDate); //입고날짜

        return "porder";

    }

}
