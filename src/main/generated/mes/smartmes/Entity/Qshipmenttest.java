package mes.smartmes.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * Qshipmenttest is a Querydsl query type for shipmenttest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qshipmenttest extends EntityPathBase<shipmenttest> {

    private static final long serialVersionUID = 1760629742L;

    public static final Qshipmenttest shipmenttest = new Qshipmenttest("shipmenttest");

    public final StringPath companyName = createString("companyName");

    public final StringPath itemQuantity = createString("itemQuantity");

    public final DateTimePath<java.time.LocalDateTime> modificationDate = createDateTime("modificationDate", java.time.LocalDateTime.class);

    public final StringPath remarks = createString("remarks");

    public final DatePath<java.time.LocalDate> shipmentDate = createDate("shipmentDate", java.time.LocalDate.class);

    public final NumberPath<Long> shipmentNo = createNumber("shipmentNo", Long.class);

    public final NumberPath<Double> supplyPrice = createNumber("supplyPrice", Double.class);

    public final NumberPath<Double> totalAmount = createNumber("totalAmount", Double.class);

    public final StringPath vat = createString("vat");

    public final NumberPath<Double> vatAmount = createNumber("vatAmount", Double.class);

    public Qshipmenttest(String variable) {
        super(shipmenttest.class, forVariable(variable));
    }

    public Qshipmenttest(Path<? extends shipmenttest> path) {
        super(path.getType(), path.getMetadata());
    }

    public Qshipmenttest(PathMetadata metadata) {
        super(shipmenttest.class, metadata);
    }

}

