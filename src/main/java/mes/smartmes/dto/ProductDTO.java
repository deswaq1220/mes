package mes.smartmes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class ProductDTO {

    private String productId;           // 제품 id


    private String division;            // 제품 구분 ( 반제품, 완제품 )

    private String productName;         // 제품명

    private int productPrice;        // 제품 가격

}
