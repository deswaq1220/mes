package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.Ratio;
import mes.smartmes.dto.Weekday;
import mes.smartmes.entity.Routing;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.RoutingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoutingService {

    private final OrdersRepository ordersRepository;
    private final RoutingRepository routingRepository;


    //상품 아이디가 들어왔을 때 routing 정보 호출
    public ArrayList<String> selectProcess(String productId){
        productId = "p001";

        Routing rout = routingRepository.findByProductId(productId);

        ArrayList<String> processList = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        temp.add(rout.getProcessNo1());
        temp.add(rout.getProcessNo2());
        temp.add(rout.getProcessNo3());
        temp.add(rout.getProcessNo4());
        temp.add(rout.getProcessNo5());
        temp.add(rout.getProcessNo6());
        temp.add(rout.getProcessNo7());
        temp.add(rout.getProcessNo8());
        temp.add(rout.getProcessNo9());
        temp.add(rout.getProcessNo10());

        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) != null) {
                processList.add(temp.get(i));
            }
        }
        return processList;
    }


    public long countTime(String productId){

        ArrayList<String> processList = selectProcess(productId);

        double workLeadTime = 0; //공정별 총 리드타임
        double workProcessTime = 0; //공정별 총 소요시간

        LocalDateTime totalProcessTime = LocalDateTime.now();

        for(int i= 0; i< processList.size(); i++){

            String process = processList.get(i);

            long leadTime = ordersRepository.findLeadTime(process);
            long processTime = ordersRepository.findProcessTime(process);
            long capa = ordersRepository.findCapa(process);
            long cnt = 0;

            switch (process){
                //process01 : 원료계량
                case "process01" :
                    workLeadTime = leadTime;
                    workProcessTime = processTime;
                    cnt = 1;
                    break;
                //process02 : 전처리
                case "process02" :
                    if(productId.equals("p001")) {

                        workLeadTime = Math.ceil(Ratio.cabbageInputQty /  1000) * leadTime;
                        workProcessTime = Ratio.cabbageInputQty / 1000 * processTime;
                        cnt = (long)Math.ceil(Ratio.cabbageInputQty /  1000);
                    }
                    else if(productId.equals("p002")){

                        workLeadTime =  Math.ceil(Ratio.garlicInputQty / 1000) * leadTime;
                        workProcessTime = Ratio.garlicInputQty / 1000 * processTime;
                        cnt = (long)Math.ceil(Ratio.garlicInputQty / 1000);
                    }
                    break;
                //process03 : 추출
                case "process03" :
                    if(productId.equals("p001")){

                        workLeadTime = Math.ceil((Ratio.cabbageWater /  2000) * leadTime);
                        workProcessTime = (Ratio.cabbageWater) / capa * processTime;
                        cnt = (long)Math.ceil((Ratio.cabbageWater /2000));

                    }else if(productId.equals("p002")){

                        workLeadTime = Math.ceil((Ratio.blackGarlicWater) / 2000) * leadTime;
                        workProcessTime = Ratio.blackGarlicWater / capa * processTime;
                        cnt = (long)Math.ceil((Ratio.blackGarlicWater /2000));

                    }
                    break;
                //process04 : 혼합 및 살균_즙
                case "process04" :
                    if (productId.equals("p001")) {

                        workProcessTime = Math.ceil(Ratio.cabbageWaterOutput /2000) * processTime;
                        cnt = (long) Math.ceil(Ratio.cabbageWaterOutput / 2000);

                    } else if (productId.equals("p002")) {

                        workProcessTime = Math.ceil(Ratio.blackGarlicOutput /  2000) * processTime;
                        cnt = (long) Math.ceil(Ratio.blackGarlicOutput / 2000);
                    }
                    break;
                //process05 : 혼합 및 살균_젤리
                case "process05" :

                    workLeadTime = Math.ceil(Ratio.jellyInputQty /  2000000);
                    workProcessTime = Math.ceil(Ratio.jellyInputQty / 2000000) * processTime;
                    cnt = (long) Math.ceil(Ratio.jellyInputQty / 2000000);
                    break;
                //process06 : 식힘
                case "process06" :
                    if(productId.equals("p001")){

                        cnt = (long) Math.ceil(Ratio.cabbageWaterOutput / 2000);
                    }
                    else if(productId.equals("p002")){

                        cnt = (long) Math.ceil(Ratio.blackGarlicOutput / 2000);
                    }
                    else {

                        cnt = (long) Math.ceil(Ratio.jellyInputQty / 2000000);
                    }
                    break;
                //process07 : 충진_즙
                case "process07" :
                    workLeadTime = leadTime;
                    workProcessTime = (double) (Ratio.waterOrderInputQty / 3500) * processTime;
                    cnt = 1;
                    break;
                //process08 : 충진_젤리
                case "process08" :
                    workLeadTime = leadTime;
                    workProcessTime = (double) Ratio.jellyOrderInputQty / 3000 * processTime;
                    cnt = 1;
                    break;
                //process09 : 검사
                case "process09" :
                    if (productId.equals("p001") || productId.equals("p002")) {

                        workLeadTime =Math.ceil(Ratio.waterOrderInputQty / 5000) * leadTime;
                        workProcessTime = ((double) Ratio.waterOrderInputQty / 5000) * processTime;
                        cnt = (long) Math.ceil(Ratio.waterOrderInputQty / 5000);

                    } else if (productId.equals("p003") || productId.equals("p004"))

                        workLeadTime =  Math.ceil(Ratio.jellyOrderInputQty /  5000) * leadTime;
                    workProcessTime = Ratio.jellyOrderInputQty / 5000 * processTime;
                    cnt = (long)Math.ceil(Ratio.jellyOrderInputQty /  5000);

                    break;
                //process10 : 포장
                case "process10" :
                    workLeadTime = leadTime;
                    workProcessTime = (long) (Ratio.orderInput / capa);
                    cnt = 1;
                    break;


            }

            ArrayList<LocalDateTime> processTimeList = new ArrayList<>();

            for(int j = 0; j < cnt; j++){

                if(j == cnt-1){
                    long time = (long) workProcessTime-(processTime*(j));
                    processTimeList.add(autoTimeCheck(totalProcessTime, leadTime, time));
                }else{
                    processTimeList.add(autoTimeCheck(totalProcessTime, leadTime, processTime));
                }
            }
            System.out.println("==========================");
            System.out.println(processTimeList);
            System.out.println("==========================");
        }



        return 0;
    }



    //현재시간을 고정 시간으로 설정
    private LocalDateTime setTime(LocalDateTime currentTime, int day, int hour) {
        currentTime = currentTime.plusDays(day).withHour(hour).withMinute(0).withSecond(0);
        return currentTime;
    }



    //요일 계산
    private LocalDateTime dayCheck(LocalDateTime currentTime){

        long workDay = ordersRepository.findWorkDay(currentTime);                     //요일 출력
        long currTime = Integer.parseInt(ordersRepository.findWorkTime(currentTime));

        //월(2) 화(3) 수(4) 목(5) 금(6) 토(7) 일(1)

        if(workDay == Weekday.SATURDAY){
            currentTime = setTime(currentTime, 2, 9);                       //토요일이면 +2일 9:00
        }
        else if(workDay == Weekday.SUNDAY){
            currentTime = setTime(currentTime, 1,9);                        //일요일이면 +1일 9:00
        }
        else if(workDay == Weekday.FRIDAY){
            if(currTime >= 180000){                                                   //금요일 18시 이후
                currentTime = setTime(currentTime, 3,9);
            }
        }else{
            return currentTime;
        }
        return currentTime;
    }



    //자동 작업 시간 계산
    private LocalDateTime autoTimeCheck(LocalDateTime currentTime, long leadTime, long workTime){
        //leadTime: 각 공정별로 계산된 리드타임
        //workTime: 각 공정별로 계산된 작업시간

        long totalTime = leadTime + workTime;
        currentTime = dayCheck(currentTime);

        //현재시간에 leadTime을 더한 시간을 %H$S%I 형태로 나타낸 것 ex) 13:50 -> 135000
        long addLeadTime = Integer.parseInt(ordersRepository.findWorkTime(currentTime.plusMinutes(leadTime)));
        System.out.println("addLeadTime = " + addLeadTime);

        //현재시간을 %H%S%I 형태로 나타낸 것
        long currTime = Integer.parseInt(ordersRepository.findWorkTime(currentTime));
        System.out.println("currTime = " +currTime);



        if((addLeadTime >= 90000 && addLeadTime < 120000)
                || (addLeadTime >= 130000 && addLeadTime < 180000)){

            if(currTime >= 120000 && currTime < 130000){
                currentTime = setTime(currentTime, 0,13);
            }
            else if(currTime >= 180000 && currTime <= 235959){
                currentTime = setTime(currentTime, 1,9);
            }
            else if(currTime < 90000){
                currentTime = setTime(currentTime,0,9);
            }else{
                currentTime = dayCheck(currentTime);
            }

        }
        else if(addLeadTime >= 120000 && addLeadTime < 130000){
            currentTime = setTime(currentTime, 0,13);
        }

        else if(addLeadTime >= 180000 && addLeadTime <= 235959){
            currentTime = setTime(currentTime, 1,9);
        }

        else if(addLeadTime < 90000){
            currentTime = setTime(currentTime, 0,9);
        }

        currentTime = dayCheck(currentTime.plusMinutes(totalTime));

        return currentTime;

    }


}