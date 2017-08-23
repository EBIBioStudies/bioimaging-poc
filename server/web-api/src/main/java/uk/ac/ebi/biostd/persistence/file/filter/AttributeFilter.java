package uk.ac.ebi.biostd.persistence.file.filter;

import lombok.Getter;
import uk.ac.ebi.biostd.model.domain.QFileAttribute;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

/**
 * Wrapper class that contains instance of {@link QFileAttribute} used to generate attributes query statments queries
 * and {@link Filter} with filter expression.
 */
@Getter
public class AttributeFilter {

    private static final String ATTR_FILTER_PREFIX = "attr_filter_";

    private final Filter filter;
    private final QFileAttribute fileAttribute;

    public AttributeFilter(Filter filter) {
        this.filter = filter;
        this.fileAttribute = new QFileAttribute(ATTR_FILTER_PREFIX + filter.getProperty());
    }
}
