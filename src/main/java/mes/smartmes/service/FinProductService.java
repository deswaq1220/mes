package mes.smartmes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mes.smartmes.entity.Finproduct;
import mes.smartmes.repository.FinproductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FinproductService {

    private final FinproductRepository finproductRepository;
    public List<Finproduct> selectList(){
        return finproductRepository.findAll();
    }

}
