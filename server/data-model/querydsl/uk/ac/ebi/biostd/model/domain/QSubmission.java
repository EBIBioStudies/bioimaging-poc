package uk.ac.ebi.biostd.model.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubmission is a Querydsl query type for Submission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSubmission extends EntityPathBase<Submission> {

    private static final long serialVersionUID = -1874849484L;

    public static final QSubmission submission = new QSubmission("submission");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath path = createString("path");

    public final ListPath<Section, QSection> sections = this.<Section, QSection>createList("sections", Section.class, QSection.class, PathInits.DIRECT2);

    public QSubmission(String variable) {
        super(Submission.class, forVariable(variable));
    }

    public QSubmission(Path<? extends Submission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubmission(PathMetadata metadata) {
        super(Submission.class, metadata);
    }

}

