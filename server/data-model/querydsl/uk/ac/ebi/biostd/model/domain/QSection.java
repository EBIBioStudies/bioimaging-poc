package uk.ac.ebi.biostd.model.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSection is a Querydsl query type for Section
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSection extends EntityPathBase<Section> {

    private static final long serialVersionUID = 849326589L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSection section = new QSection("section");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSubmission submission;

    public final NumberPath<Long> submissionId = createNumber("submissionId", Long.class);

    public final StringPath type = createString("type");

    public QSection(String variable) {
        this(Section.class, forVariable(variable), INITS);
    }

    public QSection(Path<? extends Section> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSection(PathMetadata metadata, PathInits inits) {
        this(Section.class, metadata, inits);
    }

    public QSection(Class<? extends Section> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.submission = inits.isInitialized("submission") ? new QSubmission(forProperty("submission")) : null;
    }

}

