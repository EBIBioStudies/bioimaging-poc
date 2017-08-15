package uk.ac.ebi.biostd.persistence.file.filter;

import lombok.Getter;
import uk.ac.ebi.biostd.model.domain.QFileAttribute;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;


@Getter
public class AttributeFilter {

    private final Filter filter;
    private final QFileAttribute fileAttribute;

    public AttributeFilter(Filter filter) {
        this.filter = filter;
        this.fileAttribute = new QFileAttribute("attr_filter_" + filter.getProperty());
    }
}
