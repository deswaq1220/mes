package mes.smartmes.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class ProductDTO {

    private String productId;           // 제품 id


    private String division;            // 제품 구분 ( 반제품, 완제품 )

    private String productName;         // 제품명

    private int productPrice;        // 제품 가격

}
