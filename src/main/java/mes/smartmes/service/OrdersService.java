package mes.smartmes.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.dto.Ratio;
import mes.smartmes.dto.Weekday;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.Routing;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.RoutingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    //필드 선언

    private final OrdersRepository ordersRepository;
    private final RoutingRepository rr;

    Scanner sc = new Scanner(System.in);
    double totalTime = 0;
    double workLeadTime = 0; // 공정별 리드타임
    double workProcessTime = 0; // 공정별 소요 시간

    // 현재시간 설정
    LocalDateTime totalProcessTime = LocalDateTime.now();

    // 스트링 타임 processList 배열 생성
    List<String> processList = new ArrayList<>();

    // 스트링 타입 temp 배열 생성
    List<String> temp = new ArrayList<>();


    //조회
    public String selectOrderNo() {
        String OrderIntNo = ordersRepository.findByOrderNo();
        return OrderIntNo;
    }

/*    public Orders updateOrderByOrderNo(String orderNo, OrdersDTO ordersDTO) {
    }*/

    // 삭제
    public int deleteByOrderNo(String orderNo) {
        return ordersRepository.deleteByOrderNo(orderNo);
    }


}
