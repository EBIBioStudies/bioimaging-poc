package uk.ac.ebi.biostd.persistence.common.ordering;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class OrderDirectionTest {

    @Test
    public void testGetFromString() {
        assertThat(OrderDirection.getFromString("asc")).isEqualTo(OrderDirection.ASC);
        assertThat(OrderDirection.getFromString("desc")).isEqualTo(OrderDirection.DESC);
    }
}
