package mes.smartmes.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShipment is a Querydsl query type for Shipment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShipment extends EntityPathBase<Shipment> {

    private static final long serialVersionUID = -314883716L;

    public static final QShipment shipment = new QShipment("shipment");

    public final StringPath companyName = createString("companyName");

    public final StringPath finproductNo = createString("finproductNo");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final DatePath<java.time.LocalDate> shipmentDate = createDate("shipmentDate", java.time.LocalDate.class);

    public final NumberPath<Long> shipmentNo = createNumber("shipmentNo", Long.class);

    public final NumberPath<Integer> shipmentQuantity = createNumber("shipmentQuantity", Integer.class);

    public final StringPath shipmentStatus = createString("shipmentStatus");

    public QShipment(String variable) {
        super(Shipment.class, forVariable(variable));
    }

    public QShipment(Path<? extends Shipment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShipment(PathMetadata metadata) {
        super(Shipment.class, metadata);
    }

}

