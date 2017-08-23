package uk.ac.ebi.biostd.persistence.common.ordering;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

/**
 * Rest api order expression representation class.
 *
 */
@Getter
@AllArgsConstructor
public class Order {

    private static final Pattern PATTERN = Pattern.compile("(.*)_(asc|desc)$");

    private final String property;
    private final OrderDirection direction;

    /**
     * Obtains an instance of {@link Order} based on the given expression.
     *
     * @param orderExpression the string order expression.
     * @throws IllegalArgumentException if filter expression is not correct
     * @return an instance of {@link Order} based on given order expression.
     */
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
