package uk.ac.ebi.biostd.model.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileAttView is a Querydsl query type for FileAttView
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileAttView extends EntityPathBase<FileAttView> {

    private static final long serialVersionUID = -1105750302L;

    public static final QFileAttView fileAttView = new QFileAttView("fileAttView");

    public final StringPath accNo = createString("accNo");

    public final StringPath columnKey = createString("columnKey");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath nameQualifierString = createString("nameQualifierString");

    public final NumberPath<Integer> ord = createNumber("ord", Integer.class);

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final NumberPath<Long> submission_id = createNumber("submission_id", Long.class);

    public final StringPath type = createString("type");

    public QFileAttView(String variable) {
        super(FileAttView.class, forVariable(variable));
    }

    public QFileAttView(Path<? extends FileAttView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileAttView(PathMetadata metadata) {
        super(FileAttView.class, metadata);
    }

}

