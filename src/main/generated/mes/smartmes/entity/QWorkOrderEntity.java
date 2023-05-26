package mes.smartmes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkOrderEntity is a Querydsl query type for WorkOrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkOrderEntity extends EntityPathBase<WorkOrderEntity> {

    private static final long serialVersionUID = 130867902L;

    public static final QWorkOrderEntity workOrderEntity = new QWorkOrderEntity("workOrderEntity");

    public final StringPath equipmentId = createString("equipmentId");

    public final NumberPath<Integer> orderQuantity = createNumber("orderQuantity", Integer.class);

    public final StringPath processNo = createString("processNo");

    public final StringPath prodPlanNo = createString("prodPlanNo");

    public final NumberPath<Integer> prodPlanSeq = createNumber("prodPlanSeq", Integer.class);

    public final StringPath productId = createString("productId");

    public final DateTimePath<java.time.LocalDateTime> workOrderDate = createDateTime("workOrderDate", java.time.LocalDateTime.class);

    public final StringPath workOrderNo = createString("workOrderNo");

    public final NumberPath<Integer> workOrderSeq = createNumber("workOrderSeq", Integer.class);

    public final StringPath workStatus = createString("workStatus");

    public QWorkOrderEntity(String variable) {
        super(WorkOrderEntity.class, forVariable(variable));
    }

    public QWorkOrderEntity(Path<? extends WorkOrderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkOrderEntity(PathMetadata metadata) {
        super(WorkOrderEntity.class, metadata);
    }

}

