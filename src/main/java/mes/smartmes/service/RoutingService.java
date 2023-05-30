package mes.smartmes.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import mes.smartmes.entity.*;
import mes.smartmes.entity.Process;
import mes.smartmes.repository.ProcessRepository;
import mes.smartmes.repository.RoutingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutingService {

    private final RoutingRepository routingRepository;
    public List<Routing> getAllRouting() {
        return routingRepository.findAll();
    }






}
