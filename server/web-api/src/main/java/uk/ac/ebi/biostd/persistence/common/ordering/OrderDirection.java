package uk.ac.ebi.biostd.persistence.common.ordering;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderDirection {
    ASC("asc"), DESC("desc");

    private final static Map<String, OrderDirection> SORT_ORDER_MAP;
    private final String symbol;

    static {
        SORT_ORDER_MAP = Stream.of(OrderDirection.values())
                .collect(Collectors.toMap(f -> f.symbol, f -> f));
    }

    public static OrderDirection getFromString(String value) {
        return SORT_ORDER_MAP.get(value);
    }
}
