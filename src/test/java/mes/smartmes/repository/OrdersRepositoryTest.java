package mes.smartmes.repository;

import mes.smartmes.dto.OrdersDTO;
import mes.smartmes.entity.Orders;
import mes.smartmes.service.OrdersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;

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
    @Autowired
    private OrdersService ordersService;

    private Orders orders;

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

    @Test
    public void  삭제(){

        OrdersDTO ordersDTO = new OrdersDTO();

        ordersDTO.setOrderNo("하이");
        ordersDTO.setCompanyId("CommandA");
        ordersDTO.setOrderDate(ordersDTO.getOrderDate());
        ordersDTO.setProductId("productA");

        System.out.println(ordersDTO.toString());

        ordersRepository.deleteByOrderNo("하이");
        Assertions.assertThrows(ResourceAccessException.class, () -> {
            ordersRepository.findByOrderNo("하이");

        });

    }

    @Test
    public void 저장(){

        orders = new Orders();
        orders.setOrderNo("xxxxx");

        ordersRepository.save(orders);
    }

}
