package uk.ac.ebi.biostd.persistence.file.order;

import lombok.Getter;
import uk.ac.ebi.biostd.model.domain.QFileAttribute;
import uk.ac.ebi.biostd.persistence.common.ordering.Order;

@Getter
public class AttributeOrder {

    private static final String ATTRIBUTE_PREFIX = "attr_filter_";

    private final Order order;
    private final QFileAttribute attribute;

    public AttributeOrder(Order order) {
        this.attribute = new QFileAttribute(ATTRIBUTE_PREFIX + order.getProperty());
        this.order = order;
    }
}
