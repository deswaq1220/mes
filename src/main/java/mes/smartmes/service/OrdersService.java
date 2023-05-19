package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.Ratio;
import mes.smartmes.dto.Weekday;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.Routing;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.RoutingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {


    private final OrdersRepository ordersRepository;
    private final RoutingRepository rr;

    public long selectProcessTime() {

        String productId = "p001";
        long totalTime = 0;
        long workLeadTime = 0; //공정별 리드타임
        long workProcessTime = 0; //공정별 소요시간
        LocalDateTime totalProcessTime = LocalDateTime.now();




        ArrayList<String> processList = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        Routing rout = rr.findByProductId(productId);

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


        ArrayList<LocalDateTime> process1List = new ArrayList<>();
        ArrayList<LocalDateTime> process2List = new ArrayList<>();
        ArrayList<LocalDateTime> process3List = new ArrayList<>();
        ArrayList<LocalDateTime> process4List = new ArrayList<>();
        ArrayList<LocalDateTime> process5List = new ArrayList<>();
        ArrayList<LocalDateTime> process6List = new ArrayList<>();
        ArrayList<LocalDateTime> process7List = new ArrayList<>();
        ArrayList<LocalDateTime> process8List = new ArrayList<>();
        ArrayList<LocalDateTime> process9List = new ArrayList<>();
        ArrayList<LocalDateTime> process10List = new ArrayList<>();





        for (int i = 0; i < processList.size(); i++) {

            String processno = processList.get(i);

            long leadTime = ordersRepository.findLeadTime(processno);
            long processTime = ordersRepository.findProcessTime(processno);
            long capa = ordersRepository.findCapa(processno);
            int cnt = 0;

            System.out.println(totalProcessTime.plusMinutes(workLeadTime));


            //process01 : 원료계량
            if (processList.get(i).equals("process01")) {

                workLeadTime = leadTime;
                workProcessTime = processTime;


            //process02 : 전처리
            } else if (processList.get(i).equals("process02")) {

                if (productId.equals("p001")) {
                    cnt = (int) Math.ceil(Ratio.cabbageInputQty /  1000); //공정 횟수

                    workLeadTime = (long) Math.ceil(Ratio.cabbageInputQty /  1000) * leadTime;
                    workProcessTime = (long) Ratio.cabbageInputQty / 1000 * processTime;

                } else if (productId.equals("p002")) {

                    cnt = (int)Math.ceil(Ratio.gallicInputQty / 1000); //공정 횟수
                    workLeadTime = (long) Math.ceil(Ratio.gallicInputQty / (double) 1000) * leadTime;
                    workProcessTime = (long) Ratio.gallicInputQty / 1000 * processTime;

                }

            //process03 : 추출
            } else if (processList.get(i).equals("process03")) {

                if (productId.equals("p001")) {
                    cnt = (int)Math.ceil((Ratio.cabbageWater /2000)); //공정 횟수
                    workLeadTime = (long) Math.ceil((Ratio.cabbageWater / (double) 2000) * leadTime);
                    workProcessTime = (long) (Ratio.cabbageWater) / capa * processTime;
                } else if (productId.equals("p002")) {
                    cnt = (int)Math.ceil((Ratio.blackGallicWater /2000)); //공정 횟수
                    workLeadTime = (long) Math.ceil((Ratio.blackGallicWater) / (double) 2000) * leadTime;
                    workProcessTime = (long) Ratio.blackGallicWater / capa * processTime;
                }

            //process04 : 혼합 및 살균_즙
            } else if (processList.get(i).equals("process04")) {

                if (productId.equals("p001")) {
                    cnt = (int) Math.ceil(Ratio.cabbageWaterOutput / 2000);
                    workProcessTime = (long) Math.ceil(Ratio.cabbageWaterOutput / (double) 2000) * processTime;
                } else if (productId.equals("p002")) {
                    cnt = (int) Math.ceil(Ratio.blackGallicOutput / 2000);
                    workProcessTime = (long) Math.ceil(Ratio.blackGallicOutput / (double) 2000) * processTime;
                }

            //process05 : 혼합 및 살균_젤리
            } else if (processList.get(i).equals("process05")) {
                cnt = (int) Math.ceil(Ratio.jellyInputQty / (double) 2000000);
                workLeadTime = (long) Math.ceil(Ratio.jellyInputQty / (double) 2000000);
                workProcessTime = (long) Math.ceil(Ratio.jellyInputQty / (double) 2000000) * processTime;

            //process06 : 식힘
            } else if (processList.get(i).equals("process06")) {

                if(productId.equals("p001")){
                    cnt = (int) Math.ceil(Ratio.cabbageWaterOutput / 2000);
                }else if(productId.equals("p002")){
                    cnt = (int) Math.ceil(Ratio.blackGallicOutput / 2000);
                }else {
                    cnt = (int) Math.ceil(Ratio.jellyInputQty / (double) 2000000);
                }
                totalProcessTime = totalProcessTime.plusDays(1).withHour(9).withMinute(0).withSecond(0);

            //process07 : 충진_즙
            } else if (processList.get(i).equals("process07")) {
                cnt = 1;
                workLeadTime = leadTime;
                workProcessTime = ((long) Ratio.waterOrderInputQty / 3500) * processTime;

            //process08 : 충진_젤리
            } else if (processList.get(i).equals("process08")) {
                cnt = 1;
                workLeadTime = leadTime;
                workProcessTime = ((long) Ratio.jellyOrderInputQty / 3000) * processTime;

            //process09 : 검사
            } else if (processList.get(i).equals("process09")) {

                if (productId.equals("p001") || productId.equals("p002")) {
                    cnt = (int) Math.ceil(Ratio.waterOrderInputQty / (double) 5000);
                    workLeadTime = (long) Math.ceil(Ratio.waterOrderInputQty / (double) 5000) * leadTime;
                    workProcessTime = ((long) Ratio.waterOrderInputQty / 5000) * processTime;
                } else if (productId.equals("p003") || productId.equals("p004"))
                    cnt = (int)Math.ceil(Ratio.jellyOrderInputQty / (double) 5000);
                    workLeadTime = (long) Math.ceil(Ratio.jellyOrderInputQty / (double) 5000) * leadTime;
                    workProcessTime = (long) Ratio.jellyOrderInputQty / 5000 * processTime;

            //process10 : 포장
            } else if (processList.get(i).equals("process10")) {
                cnt = 1;
                workLeadTime = leadTime;
                workProcessTime = (long) (Ratio.orderInput / capa);
            }

            ArrayList<LocalDateTime> temp2 = new ArrayList<>();

            for(int j = 0; j < cnt; j++){
                temp2.add(totalProcessTime);
                if(j == cnt-1){
                    long time = workProcessTime-(processTime*(j));
                    temp2.add(autoTimeCheck(totalProcessTime, leadTime, time));
                }else{
                    temp2.add(autoTimeCheck(totalProcessTime, leadTime, processTime));
                }

            }

            //totalProcessTime = autoTimeCheck(totalProcessTime,workLeadTime,workProcessTime);

            System.out.println("======================================================");
            System.out.println("workLeadTime = " + workLeadTime);
            System.out.println("workProcessTime = " + workProcessTime);
            System.out.println("totalProcessTime = " + totalProcessTime + "processno "+i);
            System.out.println("processno "+ i +temp2);
            System.out.println("======================================================");

        }
        return totalTime;
    }


    //현재시간을 고정 시간으로 설정
    private LocalDateTime setTime(LocalDateTime currentTime, int day, int hour) {
        currentTime = currentTime.plusDays(day).withHour(hour).withMinute(0).withSecond(0);
        return currentTime;
    }


    //요일 계산
    private LocalDateTime dayCheck(LocalDateTime currentTime){

        long workDay = ordersRepository.findWorkDay(currentTime);                     //요일 출력

        //월(2) 화(3) 수(4) 목(5) 금(6) 토(7) 일(1)

        if(workDay == Weekday.SATURDAY){
            currentTime = setTime(currentTime, 2, 9);                       //토요일이면 +2일 9:00
        }
        else if(workDay == Weekday.SUNDAY){
            currentTime = setTime(currentTime, 1,9);                        //일요일이면 +1일 9:00
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



        if((addLeadTime >= 900000 && addLeadTime < 120000)
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
                    currentTime = currentTime;
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


    //수작업 작업 시간 계산
    private LocalDateTime selfTimeCheck(LocalDateTime currentTime, int leadTime, int workTime){

        return null;
    }







}