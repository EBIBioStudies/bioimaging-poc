package uk.ac.ebi.biostd.persistence.common.custom;

import com.querydsl.jpa.impl.JPAQuery;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;

/**
 * Custom repository implementation helps
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * Execute que configured queries and retrieve and instance if {@link DataPage} with data elements and page
     * information.
     *
     * @param query the main query to be executed.
     * @param countQuery the query that retrieve that elements count after being filter.
     * @param information and instance of {@link PagingInformation} with requested page information.
     * @param listConsumer consumer action to be performed once records
     */
    DataPage<T> executePagedQuery(
            JPAQuery<T> query,
            JPAQuery<Long> countQuery,
            PagingInformation information,
            Consumer<Stream<T>> listConsumer);
}
