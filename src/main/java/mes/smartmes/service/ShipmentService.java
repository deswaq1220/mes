package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //삭제
    public int deleteByShipmentNo(String shipmentNo){

        return shipmentRepository.deleteByShipmentNo(shipmentNo);
    }




}
