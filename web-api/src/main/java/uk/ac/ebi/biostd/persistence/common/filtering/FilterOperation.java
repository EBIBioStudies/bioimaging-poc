package uk.ac.ebi.biostd.persistence.common.filtering;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

/**
 *
 */
@AllArgsConstructor
public enum FilterOperation {
    EQUALS("equals"), CONTAINS("contains");

    private static final Map<String, FilterOperation> FILTER_OPERATION_MAP;
    private final String symbol;

    static {
        FILTER_OPERATION_MAP = Stream.of(FilterOperation.values())
                .collect(Collectors.toMap(f -> f.symbol, f -> f));
    }

    public static FilterOperation getFromString(String value) {
        return FILTER_OPERATION_MAP.get(value);
    }

}
