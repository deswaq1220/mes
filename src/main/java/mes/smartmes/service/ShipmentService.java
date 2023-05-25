package mes.smartmes.service;

import com.querydsl.core.BooleanBuilder;
import mes.smartmes.entity.Orders;
import mes.smartmes.entity.QOrders;
import mes.smartmes.entity.QShipment;
import mes.smartmes.entity.Shipment;
import mes.smartmes.repository.OrdersRepository;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final OrdersRepository ordersRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository, OrdersRepository ordersRepository) {
        this.shipmentRepository = shipmentRepository;
        this.ordersRepository = ordersRepository;
    }

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
