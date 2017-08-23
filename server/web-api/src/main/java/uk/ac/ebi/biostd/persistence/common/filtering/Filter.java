package uk.ac.ebi.biostd.persistence.common.filtering;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Filter {

    private static final Pattern PATTERN = Pattern.compile("(.*)(equals|contains)(.*)$");
    public static final String PATTERN_ERROR_MSN = "Given filter %s does not conform a valid filter expression (%s)";

    private final String property;
    private final String value;
    private final FilterOperation operation;

    /**
     * Obtains an instance of {@link Filter} based on the given expression.
     *
     * @param filterExpression the string filter expression.
     * @throws IllegalArgumentException if filter expression is not correct
     * @return an instance of {@link Filter} based on given filter expression.
     */
    public static Filter getFilter(String filterExpression) {
        Matcher matcher = PATTERN.matcher(filterExpression);
        if (matcher.find()) {

            String property = matcher.group(1);
            String propertyValue = matcher.group(3);
            FilterOperation filterOperation = FilterOperation.getFromString(matcher.group(2));

            return new Filter(property, propertyValue, filterOperation);
        }

        throw new IllegalArgumentException(String.format(PATTERN_ERROR_MSN, filterExpression, PATTERN.toString()));
    }
}
