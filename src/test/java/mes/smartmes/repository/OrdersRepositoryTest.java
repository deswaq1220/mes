package mes.smartmes.repository;

import mes.smartmes.entity.Orders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void test() {
        String[] memoTexts = {"양배추즙", "흑마늘즙", "석류 젤리스틱", "매실 젤리스틱"};
        String expectedPrefix = "OD" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Orders orders = Orders.builder()
                .orderNo(expectedPrefix + String.format("%03d", i))
                .companyId(memoTexts[new Random().nextInt(memoTexts.length)])
                .build();
            ordersRepository.save(orders);
            Assertions.assertEquals(expectedPrefix + String.format("%03d", i), orders.getOrderNo());
            Assertions.assertTrue(orders.getOrderNo().startsWith(expectedPrefix));
        });
    }


}
