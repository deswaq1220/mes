package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
import org.springframework.transaction.annotation.Transactional;
=======

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
>>>>>>> Stashed changes

@Service
@Transactional
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;


    //조회
    public String selectShipmentNo(){
        String shipmentIntNo = shipmentRepository.findByShipmentNo();
        return  shipmentIntNo;
    }

<<<<<<< Updated upstream
    //삭제
    public int deleteByShipmentNo(String shipmentNo){
=======
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();

    }




    // 다중검색

    @Transactional
    public List<Shipment> searchShipment(String shipmentNo, LocalDate startDate, LocalDate endDate, String companyName) {
        QShipment qShipment = QShipment.shipment;
        BooleanBuilder builder = new BooleanBuilder();


        if (shipmentNo != null && shipmentNo != "") {
            builder.and(qShipment.shipmentNo.contains(shipmentNo)); //출하번호
        }


        if (companyName != null && companyName != "") {
            builder.and(qShipment.companyName.contains(companyName)); // 거래처
        }

        if (startDate != null && endDate != null) {
            builder.and(qShipment.shipmentDate.between(startDate, endDate)); // 날짜
        }

        return (List<Shipment>) shipmentRepository.findAll(builder);

//        return (List<Shipment>) shipmentRepository.findAll(builder.getValue());
    }


    //오더 리스트 다중검색
    @Transactional
    public List<Orders> searchOrders(String orderNo , String productId, LocalDate startDate, LocalDate endDate){
        QOrders qOrders = QOrders.orders;
        BooleanBuilder builder = new BooleanBuilder();

        if(orderNo != null && orderNo != ""){
            builder.and(qOrders.orderNo.contains(orderNo));
        }
        if(productId != null && productId != ""){
            builder.and(qOrders.productId.contains(productId));
        }

        if (startDate != null && endDate != null) {
            builder.and(qOrders.orderDate.between(startDate, endDate)); // 날짜
        }


        return (List<Orders>) ordersRepository.findAll(builder);
>>>>>>> Stashed changes

        return shipmentRepository.deleteByShipmentNo(shipmentNo);
    }




}
