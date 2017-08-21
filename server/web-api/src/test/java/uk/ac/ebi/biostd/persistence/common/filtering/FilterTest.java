package uk.ac.ebi.biostd.persistence.common.filtering;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FilterTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void TestGetFilterWhenInvalidPattern() {
        expectedException.expect(IllegalArgumentException.class);

        Filter.getFilter("field1=10");
    }

    @Test
    public void TestGetFilter() {
        Filter filter = Filter.getFilter("field1equals10");
        assertThat(filter.getOperation()).isEqualTo(FilterOperation.EQUALS);
        assertThat(filter.getProperty()).isEqualTo("field1");
        assertThat(filter.getValue()).isEqualTo("10");
    }

}