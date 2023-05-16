//package mes.smartmes.Entity;
//
//import javax.persistence.*;
//import java.lang.String;
//
////@Entity
////@Table(name = "shipment")
////public class shipmentEntity {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    @Column(name = "shipment_no")
////    private Long shipmentNo; // 출하관리번호 pk
////
////    @ManyToOne
////    @JoinColumn(name = "finproduct_no")
////    private String finproduct_no; // 완제품 번호fk
////
////    @Column(name = "company_name")
////    private String companyName; // 출하 업체 이름
////
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "product_id")
////    //private Product productId; // 출하 제품 id
////
////    @Column(name = "shipment_quantity")
////    private int shipmentQuantity; // 출하 수량
////
////    @Column(name = "shipment_status")
////    private String shipmentStatus; // 출하 상태
////
//}
