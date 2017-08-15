package uk.ac.ebi.biostd.rest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilePagingResponse {

    private final long totalElements;
    private final int totalPages;
    private final int pageNumber;
    private final int pageSize;

    private final List<FileDTO> files;
}
