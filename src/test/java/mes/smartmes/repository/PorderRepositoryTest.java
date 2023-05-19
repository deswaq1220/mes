package mes.smartmes.repository;

import mes.smartmes.entity.Porder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@SpringBootTest
public class PorderRepositoryTest {

    @Autowired
    PorderRepository pr;

    Porder porder;
    @Test
    public void test() {

        String dayNo = "PD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int porderIntNo = Integer.parseInt(pr.findByPorderNo()) + 1;

        Porder porder = Porder.builder()
                .porderNo(dayNo + Integer.toString(porderIntNo))
                .porderDate(LocalDateTime.now())
                .ingredientId("12345")
                .porderQuantity(5)
                .supplierId("55555")
                .emergency_yn('N')
                .porderStatus('N')
                .build();
        pr.save(porder);


        System.out.println(pr);

    }


}