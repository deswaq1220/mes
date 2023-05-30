package mes.smartmes.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.QOrders;
import mes.smartmes.entity.QShipment;
import mes.smartmes.entity.Shipment;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {

    private  final ShipmentRepository shipmentRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository, OrdersRepository ordersRepository) {
        this.shipmentRepository = shipmentRepository;
        this.ordersRepository = ordersRepository;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }



    //조회
    public String selectShipmentNo(){
        String shipmentIntNo = shipmentRepository.findByShipmentNo();
        return  shipmentIntNo;
    }

    //삭제
    public int deleteByShipmentNo(String shipmentNo){

        return shipmentRepository.deleteByShipmentNo(shipmentNo);
    }

    // 다중검색

    @javax.transaction.Transactional
    public List<Shipment> searchShipment(String shipmentNo, LocalDateTime startDate, LocalDateTime endDate, String companyName) {
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
    @javax.transaction.Transactional
    public List<Orders> searchOrders(String orderNo , String productId){
        QOrders qOrders = QOrders.orders;
        BooleanBuilder builder = new BooleanBuilder();

        if(orderNo != null && orderNo != ""){
            builder.and(qOrders.orderNo.contains(orderNo));
        }
        if(productId != null && productId != ""){
            builder.and(qOrders.productId.contains(productId));
        }

        return (List<Orders>) ordersRepository.findAll(builder);

    }




}
