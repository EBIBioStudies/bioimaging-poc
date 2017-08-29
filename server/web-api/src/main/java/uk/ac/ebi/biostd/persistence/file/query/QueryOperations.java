package uk.ac.ebi.biostd.persistence.file.query;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import uk.ac.ebi.biostd.persistence.file.filter.AttributeFilter;
import uk.ac.ebi.biostd.persistence.file.filter.FileFilter;
import uk.ac.ebi.biostd.persistence.file.order.AttributeOrder;
import uk.ac.ebi.biostd.persistence.file.order.FileOrder;
import uk.ac.ebi.biostd.services.files.meta.QueryMetadata;

@Builder
@Getter
public class QueryOperations {

    private final List<AttributeFilter> attrFilters;
    private final List<AttributeOrder> attrOrders;
    private final List<FileFilter> fileFilters;
    private final List<FileOrder> fileOrders;
    private final QueryMetadata queryMetadata;
}
