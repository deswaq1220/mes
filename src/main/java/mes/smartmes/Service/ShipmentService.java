package mes.smartmes.Service;

import com.querydsl.core.BooleanBuilder;
import mes.smartmes.Entity.QShipment;
import mes.smartmes.Entity.Shipment;
import mes.smartmes.Entity.shipmenttest;
import mes.smartmes.dto.shipmentDto;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }



//    public List<Shipment> performSearch(String company, String startDate, String endDate, String finproductNo, String product, String partner) {
//        List<Shipment> searchResults = shipmentRepository.searchByConditions(company, startDate, endDate, finproductNo, product, partner);
//
//        return searchResults;
//    }

    @Transactional
    public List<Shipment> searchShipment(String company, String finproductNo, LocalDate startDate, LocalDate endDate, String companyName, String item) {
        QShipment qShipment = QShipment.shipment;
        BooleanBuilder builder = new BooleanBuilder();

        if (company != null && company != "") {
            builder.and(qShipment.company.contains(company)); // 회사명
        }


        if (finproductNo != null && finproductNo != "") {
            builder.and(qShipment.finproductNo.contains(finproductNo)); //출하번호
        }


        if (companyName != null) {
            builder.and(qShipment.companyName.contains(companyName)); // 거래처
        }

        if (item != null) {
            builder.and(qShipment.item.contains(item)); // 거래처
        }



        if (startDate != null && endDate != null) {
            builder.and(qShipment.shipmentDate.between(startDate, endDate)); // 날짜
        }

        return (List<Shipment>) shipmentRepository.findAll(builder);

//        return (List<Shipment>) shipmentRepository.findAll(builder.getValue());
    }








}
