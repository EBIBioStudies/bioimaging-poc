package uk.ac.ebi.biostd.persistence.paging;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DataPage<T> {

    private final long total;
    private final int pageNumber;
    private final int pageSize;
    private final int totalPages;
    private final List<T> elements;
}
