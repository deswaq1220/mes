package mes.smartmes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdPerformEntity is a Querydsl query type for ProdPerformEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProdPerformEntity extends EntityPathBase<ProdPerformEntity> {

    private static final long serialVersionUID = 566863563L;

    public static final QProdPerformEntity prodPerformEntity = new QProdPerformEntity("prodPerformEntity");

    public final StringPath equipmentId = createString("equipmentId");

    public final StringPath processNo = createString("processNo");

    public final DateTimePath<java.time.LocalDateTime> productionDate = createDateTime("productionDate", java.time.LocalDateTime.class);

    public final StringPath productionId = createString("productionId");

    public final StringPath productionNo = createString("productionNo");

    public final NumberPath<Integer> productionQuantity = createNumber("productionQuantity", Integer.class);

    public final NumberPath<Integer> productionSeq = createNumber("productionSeq", Integer.class);

    public final StringPath productionStatus = createString("productionStatus");

    public final StringPath workOrderNo = createString("workOrderNo");

    public final NumberPath<Integer> workOrderSeq = createNumber("workOrderSeq", Integer.class);

    public QProdPerformEntity(String variable) {
        super(ProdPerformEntity.class, forVariable(variable));
    }

    public QProdPerformEntity(Path<? extends ProdPerformEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdPerformEntity(PathMetadata metadata) {
        super(ProdPerformEntity.class, metadata);
    }

}

