package uk.ac.ebi.biostd.persistence.common.custom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.querydsl.jpa.impl.JPAQuery;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;

@RunWith(MockitoJUnitRunner.class)
public class CustomRepositoryImplTest {

    @Mock
    private JPAQuery<DummyEntity> query;

    @Mock
    private JPAQuery<Long> countQuery;

    @Mock
    private Consumer<Stream<DummyEntity>> listConsumer;

    @Mock
    private JpaEntityInformation mockEntityInformation;

    @Mock
    private EntityManager mockEntityManager;

    private CustomRepositoryImpl<DummyEntity, Long> testInstance;

    @Before
    public void setup() {
        when(mockEntityManager.getDelegate()).thenReturn(mockEntityManager);

        testInstance = new CustomRepositoryImpl<>(mockEntityInformation, mockEntityManager);
    }

    @Test
    public void executePagedQuery() throws Exception {
        when(countQuery.fetchOne()).thenReturn(1001L);
        when(query.offset(anyInt())).thenReturn(query);
        when(query.limit(anyInt())).thenReturn(query);
        when(query.fetch()).thenReturn(Collections.emptyList());

        DataPage<DummyEntity> dataPage = testInstance.executePagedQuery(
                query,
                countQuery,
                new PagingInformation(2, 20),
                listConsumer);

        verify(query).offset(40);
        verify(query).limit(20);
        assertThat(dataPage.getElements()).isEqualTo(Collections.emptyList());
    }

    @Getter
    @AllArgsConstructor
    private class DummyEntity {

        private Long id;
        private String field;
    }

}