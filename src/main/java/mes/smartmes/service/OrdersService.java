package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.Orders;
import mes.smartmes.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class OrdersService {
    Orders orders;


}
