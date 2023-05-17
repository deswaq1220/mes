package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Porder;
import mes.smartmes.repository.PorderRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PorderService {

    private final PorderRepository pr;
    public String selectPorderNo(){

        String porderIntNo = pr.findByPorderNo();

        return porderIntNo;
    }
    public List<Porder> selectPorderList(){
       return pr.findAll();
    }
    public Integer selectTime(String porderNo){
        return Integer.parseInt(pr.selectTime(porderNo));
    }

    public Integer selectDay(String porderNo){
        return pr.selectDay(porderNo);
    }

    public LocalDateTime selectDate(String porderNo){
        return pr.selectDate(porderNo);
    }

    public String selectEmergencyYn(String porderNo){
        return pr.emergencyYn(porderNo);
    }



}
