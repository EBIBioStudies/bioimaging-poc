package uk.ac.ebi.biostd.persistence.common.custom;

import com.querydsl.jpa.impl.JPAQuery;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;

/**
 * Custom repository implementation.
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
        CustomRepository<T, ID> {

    public CustomRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public DataPage<T> executePagedQuery(
            JPAQuery<T> query,
            JPAQuery<Long> countQuery,
            PagingInformation information,
            Consumer<Stream<T>> listConsumer) {

        int pageNumber = information.getPage();
        int pageSize = information.getSize();
        long total = countQuery.fetchOne();
        int totalPages = Math.toIntExact(total / pageSize);
        List<T> elements = query.offset(pageNumber * pageSize).limit(pageSize).fetch();
        listConsumer.accept(elements.stream());
        return new DataPage<>(total, pageNumber, pageSize, totalPages, elements);
    }
}