package uk.ac.ebi.biostd.services.files;


import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;
import uk.ac.ebi.biostd.persistence.common.ordering.Order;
import uk.ac.ebi.biostd.persistence.common.ordering.OrderDirection;
import uk.ac.ebi.biostd.persistence.file.query.FilesQueryService;
import uk.ac.ebi.biostd.persistence.file.query.QueryOperations;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;
import uk.ac.ebi.biostd.persistence.repositories.FileAttributeRepository;
import uk.ac.ebi.biostd.persistence.repositories.FilesRepository;
import uk.ac.ebi.biostd.services.meta.MetadataService;


@Service
@AllArgsConstructor
public class FileService {

    private static final String OPERATIONS_SEPARATOR = ",";
    private static final Order DEFAULT_ORDER = new Order("order", OrderDirection.ASC);

    private final FilesRepository filesRepository;
    private final FileAttributeRepository attributeRepository;
    private final MetadataService metadataService;
    private final FilesQueryService filesQueryService;

    public DataPage<File> getFilePage(
            long submissionId,
            PagingInformation pagingInformation,
            Optional<String> filters,
            Optional<String> orders) {

        List<Filter> filterList = getFilterList(filters);
        List<Order> orderList = getOrder(orders);

        QueryOperations queryOperations = filesQueryService
                .getQueryOperations(metadataService.getMetadata(submissionId), filterList, orderList);

        return filesRepository.executePagedQuery(
                filesQueryService.getFilesQuery(submissionId, queryOperations),
                filesQueryService.getCountQuery(submissionId, queryOperations),
                pagingInformation,
                files -> attributeRepository.findByFileIdIn(files.map(File::getId).collect(toList())));
    }

    private List<Order> getOrder(Optional<String> orders) {
        return orders.map(ord -> stream(ord.split(OPERATIONS_SEPARATOR)).map(Order::getOrder).collect(toList()))
                .orElse(singletonList(DEFAULT_ORDER));
    }

    private List<Filter> getFilterList(Optional<String> filters) {
        return filters.map(ord -> stream(ord.split(OPERATIONS_SEPARATOR)).map(Filter::getFilter).collect(toList()))
                .orElse(emptyList());
    }
}
