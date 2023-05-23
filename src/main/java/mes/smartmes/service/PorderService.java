package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.Weekday;
import mes.smartmes.entity.IngredientInput;
import mes.smartmes.entity.IngredientStock;
import mes.smartmes.entity.Ingredients;
import mes.smartmes.entity.Porder;
import mes.smartmes.repository.IngredientInputRepository;
import mes.smartmes.repository.IngredientStockRepository;
import mes.smartmes.repository.PorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PorderService {

    private final PorderRepository porderRepository;



    // 은영

    //발주 내역 리스트
    public List<Porder> selectPorderList() {
        return porderRepository.findAll();
    }

    //현재시간을 고정 시간으로 설정
    private LocalDateTime setTime(LocalDateTime currentTime, int day, int hour) {
        currentTime = currentTime.plusDays(day).withHour(hour).withMinute(0).withSecond(0);
        return currentTime;
    }

    //입고 예정 일자 생성
    public LocalDateTime selectInputDate(String porderNo) {


        int inputTime = Integer.parseInt(porderRepository.selectTime(porderNo));            //시간

        int inputDay = porderRepository.selectDay(porderNo);                                //요일

        LocalDateTime inputDate = porderRepository.selectDate(porderNo);                    //발주 날짜(발주 등록 날짜랑 다름)

        LocalDateTime inputIngreDate = inputDate;                                           //입고날짜

        //12시 이후 주문 시 발주 날짜는 +1일
        if (inputTime > 120000) {
            inputDate = inputDate.plusDays(1);
            inputDay = inputDay + 1;
        }

        String emerYn = porderRepository.emergencyYn(porderNo);                             //긴급 입고 여부 확인

        String ingre = porderRepository.selectIngreName(porderNo);




        // 입고 예정 시간


        // 재고 관리 시  입고 예정 시간 이후면 발주 한거에서 현재시간이 입고예정시간 보다 크거나 같으면 입고 대기에서 입고완료로 바뀌고
        // 자재 입고테이블로 인서트문 넣고?               // 입고테이블 입고 내역
        // 재고 관리에서 인서트??                        //  현재 재고





        if (ingre.equals("I001") || ingre.equals("I002") || ingre.equals("I006") || ingre.equals("I007") || ingre.equals("I008")) {
            if (emerYn.equals("N")) {
                if (inputDay + 2 == Weekday.MONDAY || inputDay + 2 == Weekday.WEDNESDAY || inputDay + 2 == Weekday.FRIDAY) {
                    inputIngreDate = setTime(inputDate, 2, 10);
                } else if (inputDay + 2 == Weekday.SUNDAY || inputDay + 2 == Weekday.TUESDAY || inputDay + 2 == Weekday.THURSDAY) {
                    inputIngreDate = setTime(inputDate, 3, 10);
                } else if (inputDay + 2 == Weekday.SATURDAY) {
                    inputIngreDate = setTime(inputDate, 4, 10);
                }
            } else {
                return inputIngreDate;
            }


        } else if (ingre.equals("I003") || ingre.equals("I004") || ingre.equals("I005")) {
            if (emerYn.equals("N")) {
                if (inputDay + 3 == Weekday.MONDAY || inputDay + 3 == Weekday.WEDNESDAY || inputDay + 3 == Weekday.FRIDAY) {
                    inputIngreDate = setTime(inputDate, 2, 10);
                } else if (inputDay + 3 == Weekday.SUNDAY || inputDay + 3 == Weekday.TUESDAY || inputDay + 3 == Weekday.THURSDAY) {
                    inputIngreDate = setTime(inputDate, 3, 10);
                } else if (inputDay + 3 == Weekday.SATURDAY) {
                    inputIngreDate = setTime(inputDate, 4, 10);
                }
            } else {
                return inputIngreDate;
            }
        }


        System.out.println("ingre = " + ingre);
        System.out.println(inputIngreDate);
        return inputIngreDate;
    }


    //희람




}

