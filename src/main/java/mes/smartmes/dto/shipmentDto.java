package mes.smartmes.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class shipmentDto {

    private String companyName;
    private LocalDate shipmentDate;
    private Long shipmentNo;
    private String vat;
    private String itemQuantity;
    private Double supplyPrice;
    private Double vatAmount;
    private Double totalAmount;
    private String remarks;
    private LocalDateTime modificationDate;

}
