package uk.ac.ebi.biostd.model.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileAttribute is a Querydsl query type for FileAttribute
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileAttribute extends EntityPathBase<FileAttribute> {

    private static final long serialVersionUID = -967589480L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileAttribute fileAttribute = new QFileAttribute("fileAttribute");

    public final QFile file;

    public final NumberPath<Long> file_id = createNumber("file_id", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final StringPath value = createString("value");

    public QFileAttribute(String variable) {
        this(FileAttribute.class, forVariable(variable), INITS);
    }

    public QFileAttribute(Path<? extends FileAttribute> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFileAttribute(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFileAttribute(PathMetadata metadata, PathInits inits) {
        this(FileAttribute.class, metadata, inits);
    }

    public QFileAttribute(Class<? extends FileAttribute> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.file = inits.isInitialized("file") ? new QFile(forProperty("file"), inits.get("file")) : null;
    }

}

