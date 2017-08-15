package uk.ac.ebi.biostd.persistence.file.filter;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import lombok.experimental.UtilityClass;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

@UtilityClass
public class PredicateUtil {

    public Predicate getFilterPredicate(FileFilter fileFilter) {
        Filter filter = fileFilter.getFilter();
        QFile file = fileFilter.getFile();

        switch (fileFilter.getFilter().getProperty()) {
            case "name":
                return getFilterExpression(filter, file.name);
            default:
                throw new IllegalArgumentException("Not supported filter operation");
        }

    }

    public BooleanExpression getFilterExpression(Filter filter, StringPath path) {

        switch (filter.getOperation()) {
            case EQUALS:
                return path.eq(filter.getValue());
            case CONTAINS:
                return path.lower().like("%" + filter.getValue().toLowerCase() + "%");
            default:
                throw new IllegalArgumentException("Not supported filter operation");
        }
    }
}
