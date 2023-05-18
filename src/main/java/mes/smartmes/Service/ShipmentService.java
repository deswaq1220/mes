package mes.smartmes.Service;

import mes.smartmes.Entity.Shipment;
import mes.smartmes.Entity.shipmenttest;
import mes.smartmes.dto.shipmentDto;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<shipmenttest> getAllShipments() {
        return shipmentRepository.findAll();
    }


}
