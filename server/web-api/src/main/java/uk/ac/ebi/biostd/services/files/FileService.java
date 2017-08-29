package uk.ac.ebi.biostd.services.files;


import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
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
import uk.ac.ebi.biostd.services.files.meta.MetadataService;


@Service
@AllArgsConstructor
public class FileService {

    private static final String SEPARATOR = ",";
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
        return getOperationsList(orders, Order::getOrder).orElse(Collections.singletonList(DEFAULT_ORDER));
    }

    private List<Filter> getFilterList(Optional<String> filters) {
        return getOperationsList(filters, Filter::getFilter).orElse(Collections.emptyList());
    }

    private <T> Optional<List<T>> getOperationsList(Optional<String> operations, Function<String, T> function) {
        return operations.map(value -> stream(value.split(SEPARATOR)).map(function::apply).collect(toList()));
    }
}
