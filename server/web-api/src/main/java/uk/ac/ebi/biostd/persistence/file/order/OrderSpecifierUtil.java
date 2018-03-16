package uk.ac.ebi.biostd.persistence.file.order;

import com.google.common.collect.Lists;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import java.util.List;
import lombok.experimental.UtilityClass;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.model.domain.QFileAttribute;
import uk.ac.ebi.biostd.persistence.common.ordering.Order;
import uk.ac.ebi.biostd.persistence.common.ordering.OrderDirection;
import uk.ac.ebi.biostd.persistence.file.query.QueryOperations;

@UtilityClass
public class OrderSpecifierUtil {

    public OrderSpecifier[] getOrderSpecification(QueryOperations queryOperations) {
        List<OrderSpecifier> orders = Lists.newArrayList();
        queryOperations.getAttrOrders().forEach(ord -> orders.add(getOrder(ord.getOrder(), ord.getAttribute())));
        queryOperations.getFileOrders().forEach(ord -> orders.add(getOrder(ord.getOrder(), ord.getFile())));
        return orders.toArray(new OrderSpecifier[orders.size()]);
    }

    private OrderSpecifier getOrder(Order order, QFileAttribute fileAttribute) {
        return order.getDirection() == OrderDirection.DESC
                ? fileAttribute.value.desc()
                : fileAttribute.value.asc();
    }

    private OrderSpecifier getOrder(Order order, QFile fileAttribute) {
        ComparableExpressionBase filePath = null;

        switch (order.getProperty()) {
            case "name":
                filePath = QFile.file.name;
                break;
            case "size":
                filePath = QFile.file.size;
                break;
            case "order":
                filePath = QFile.file.order;
        }

        return order.getDirection() == OrderDirection.DESC ? filePath.desc() : filePath.asc();
    }
}
