package mes.smartmes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPorder is a Querydsl query type for Porder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPorder extends EntityPathBase<Porder> {

    private static final long serialVersionUID = 1718189408L;

    public static final QPorder porder = new QPorder("porder");

    public final StringPath emergencyYn = createString("emergencyYn");

    public final StringPath ingredientId = createString("ingredientId");

    public final DateTimePath<java.time.LocalDateTime> porderDate = createDateTime("porderDate", java.time.LocalDateTime.class);

    public final StringPath porderNo = createString("porderNo");

    public final NumberPath<Integer> porderQuantity = createNumber("porderQuantity", Integer.class);

    public final StringPath porderStatus = createString("porderStatus");

    public final StringPath supplierId = createString("supplierId");

    public QPorder(String variable) {
        super(Porder.class, forVariable(variable));
    }

    public QPorder(Path<? extends Porder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPorder(PathMetadata metadata) {
        super(Porder.class, metadata);
    }

}

