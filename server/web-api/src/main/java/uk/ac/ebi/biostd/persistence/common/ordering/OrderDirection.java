package uk.ac.ebi.biostd.persistence.common.ordering;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

/**
 * Determinate api order direction.
 *
 */
@AllArgsConstructor
public enum OrderDirection {
    ASC("asc"), DESC("desc");

    private final static Map<String, OrderDirection> SORT_ORDER_MAP;
    private final String symbol;

    static {
        SORT_ORDER_MAP = Stream.of(OrderDirection.values())
                .collect(Collectors.toMap(order -> order.symbol, order -> order));
    }

    public static OrderDirection getFromString(String value) {
        return SORT_ORDER_MAP.get(value);
    }
}
