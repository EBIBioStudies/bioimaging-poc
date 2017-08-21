package uk.ac.ebi.biostd.persistence.common.ordering;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OrderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetOrderWhenAsc() {
        Order order = Order.getOrder("field1_asc");
        assertThat(order.getDirection()).isEqualTo(OrderDirection.ASC);
        assertThat(order.getProperty()).isEqualTo("field1");
    }

    @Test
    public void testGetOrderWhenDesc() {
        Order order = Order.getOrder("field2_desc");
        assertThat(order.getDirection()).isEqualTo(OrderDirection.DESC);
        assertThat(order.getProperty()).isEqualTo("field2");
    }

    @Test
    public void testGetOrderWhenInvalidPattern() {
        expectedException.expect(IllegalArgumentException.class);
        Order.getOrder("field2");
    }
}