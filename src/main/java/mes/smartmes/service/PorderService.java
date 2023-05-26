package mes.smartmes.service;

import mes.smartmes.dto.Weekday;
import mes.smartmes.entity.Porder;
import mes.smartmes.repository.PorderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PorderService {

    private final PorderRepository porderRepository;

    public List<Porder> selectList(){
        return porderRepository.findAll();
    }

    public PorderService(PorderRepository porderRepository) {
        this.porderRepository = porderRepository;
    }


    public List<Porder> findSearch(java.util.Date startDate, java.util.Date endDate, String porderStatus, String supplierId) {
        return porderRepository.findSearch(startDate, endDate, porderStatus, supplierId);
    }

    //발주 내역 리스트
    public List<Porder> selectPorderList() {
        return porderRepository.findAll();
    }
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

        if (ingre.equals("양배추") || ingre.equals("흑마늘") || ingre.equals("파우치") || ingre.equals("스틱파우치") || ingre.equals("포장Box")) {
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
        } else if (ingre.equals("석류농축액") || ingre.equals("매실농축액") || ingre.equals("콜라겐")) {
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


        return inputIngreDate;
    }





}

