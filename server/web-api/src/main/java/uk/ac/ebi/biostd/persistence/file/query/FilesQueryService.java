package uk.ac.ebi.biostd.persistence.file.query;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import com.google.common.collect.Lists;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.model.domain.QFileAttribute;
import uk.ac.ebi.biostd.model.domain.QSection;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;
import uk.ac.ebi.biostd.persistence.common.ordering.Order;
import uk.ac.ebi.biostd.persistence.file.filter.AttributeFilter;
import uk.ac.ebi.biostd.persistence.file.filter.FileFilter;
import uk.ac.ebi.biostd.persistence.file.filter.PredicateUtil;
import uk.ac.ebi.biostd.persistence.file.order.AttributeOrder;
import uk.ac.ebi.biostd.persistence.file.order.FileOrder;
import uk.ac.ebi.biostd.persistence.file.order.OrderSpecifierUtil;
import uk.ac.ebi.biostd.services.files.meta.QueryMetadata;

@Component
@AllArgsConstructor
public class FilesQueryService {

    private final static QFile Q_FILE = QFile.file;
    private final static QSection Q_SECTION = QSection.section;

    private final JPAQueryFactory jpaQueryFactory;

    public JPAQuery<File> getFilesQuery(long submissionId, QueryOperations operations) {
        return jpaQueryFactory.select(Q_FILE)
                .from(getEntityPaths(operations.getAttrFilters(), operations.getAttrOrders()))
                .where(getWherePredicates(
                        submissionId,
                        operations.getAttrFilters(),
                        operations.getAttrOrders(),
                        operations.getFileFilters(),
                        operations.getQueryMetadata()))
                .orderBy(OrderSpecifierUtil.getOrderSpecification(operations));
    }

    public JPAQuery<Long> getCountQuery(long submissionId, QueryOperations operations) {
        return jpaQueryFactory.select(Q_FILE.count())
                .from(getEntityPaths(operations.getAttrFilters(), emptyList()))
                .where(getWherePredicates(
                        submissionId,
                        operations.getAttrFilters(),
                        emptyList(),
                        operations.getFileFilters(),
                        operations.getQueryMetadata()));
    }

    private EntityPath[] getEntityPaths(List<AttributeFilter> filters, List<AttributeOrder> orders) {
        List<EntityPath> entityPaths = Lists.newArrayList(Q_FILE, Q_SECTION);
        filters.forEach(filter -> entityPaths.add(filter.getFileAttribute()));
        orders.forEach(order -> entityPaths.add(order.getAttribute()));

        return entityPaths.toArray(new EntityPath[entityPaths.size()]);
    }

    private Predicate[] getWherePredicates(long submissionId, List<AttributeFilter> filters,
            List<AttributeOrder> orders, List<FileFilter> fileFilters, QueryMetadata queryMetadata) {

        List<Predicate> wherePredicates = Lists.newArrayList();
        wherePredicates.add(Q_SECTION.id.eq(Q_FILE.sectionId));
        wherePredicates.add(Q_SECTION.submissionId.eq(submissionId));

        filters.forEach(attrFilter -> {
            QFileAttribute attr = attrFilter.getFileAttribute();
            Filter filter = attrFilter.getFilter();

            wherePredicates.add(attr.file_id.eq(Q_FILE.id));
            wherePredicates.add(attr.name.eq(queryMetadata.getName(filter.getProperty())));
            wherePredicates.add(PredicateUtil.getFilterExpression(filter, attr.value));
        });

        orders.forEach(order -> {
            QFileAttribute fileAttr = order.getAttribute();
            wherePredicates.add(fileAttr.file_id.eq(Q_FILE.id));
            wherePredicates.add(fileAttr.name.eq(queryMetadata.getName(order.getOrder().getProperty())));
        });

        fileFilters.forEach(fileFilter -> wherePredicates.add(PredicateUtil.getFilterPredicate(fileFilter)));
        return wherePredicates.toArray(new Predicate[wherePredicates.size()]);
    }


    public QueryOperations getQueryOperations(QueryMetadata metadata, List<Filter> filters, List<Order> orders) {

        Map<Boolean, List<Filter>> filterMap = getFilterMap(metadata, filters);
        Map<Boolean, List<Order>> orderMap = getOrdersMap(metadata, orders);

        return QueryOperations.builder()
                .fileFilters(getFileFilters(filterMap))
                .attrFilters(getAttributeFilters(filterMap))
                .fileOrders(getFileOrders(orderMap))
                .attrOrders(getAttributeOrder(orderMap))
                .queryMetadata(metadata)
                .build();
    }

    private List<FileOrder> getFileOrders(Map<Boolean, List<Order>> orderMap) {
        return orderMap.getOrDefault(true, emptyList()).stream().map(f -> new FileOrder(f, Q_FILE)).collect(toList());
    }

    private List<AttributeOrder> getAttributeOrder(Map<Boolean, List<Order>> orders) {
        return orders.getOrDefault(false, emptyList()).stream().map(AttributeOrder::new).collect(toList());
    }

    private List<AttributeFilter> getAttributeFilters(Map<Boolean, List<Filter>> filterMap) {
        return filterMap.getOrDefault(false, emptyList()).stream().map(AttributeFilter::new).collect(toList());
    }

    private List<FileFilter> getFileFilters(Map<Boolean, List<Filter>> filterMap) {
        return filterMap.getOrDefault(true, emptyList()).stream().map(f -> new FileFilter(f, Q_FILE)).collect(toList());
    }

    private Map<Boolean, List<Order>> getOrdersMap(QueryMetadata descriptors, List<Order> orders) {
        return orders.stream().collect(groupingBy(o -> descriptors.isFileProp(o.getProperty())));
    }

    private Map<Boolean, List<Filter>> getFilterMap(QueryMetadata descriptors, List<Filter> filters) {
        return filters.stream().collect(groupingBy(f -> descriptors.isFileProp(f.getProperty())));
    }

}
