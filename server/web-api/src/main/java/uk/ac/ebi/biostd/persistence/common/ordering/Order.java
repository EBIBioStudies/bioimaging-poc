package uk.ac.ebi.biostd.persistence.common.ordering;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {

    private static final Pattern PATTERN = Pattern.compile("(.*)_(asc|desc)$");

    private final String property;
    private final OrderDirection direction;

    public static Order getOrder(String orderExpression) {
        Matcher matcher = PATTERN.matcher(orderExpression);
        if (matcher.find()) {

            String firstProperty = matcher.group(1);
            String sort = matcher.group(2);

            return new Order(firstProperty, OrderDirection.getFromString(sort));
        }

        throw new IllegalArgumentException(
                String.format("Given order %s does not conform a valid order expression", orderExpression));
    }
}
