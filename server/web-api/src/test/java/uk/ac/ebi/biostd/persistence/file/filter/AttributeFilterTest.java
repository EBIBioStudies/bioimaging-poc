package uk.ac.ebi.biostd.persistence.file.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

@RunWith(MockitoJUnitRunner.class)
public class AttributeFilterTest {

    private static final String PROPERTY = "a_property";

    @Mock
    private Filter filter;

    @Before
    public void setup() {
        when(filter.getProperty()).thenReturn(PROPERTY);
    }

    @Test
    public void testAttributeFilter() {
        AttributeFilter attrFilter = new AttributeFilter(filter);
        assertThat(attrFilter.getFileAttribute().toString()).isEqualTo("attr_filter_a_property");
        assertThat(attrFilter.getFileAttribute()).isNotNull();
    }
}