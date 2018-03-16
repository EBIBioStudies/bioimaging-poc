package uk.ac.ebi.biostd.rest.resources;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.ebi.biostd.model.data.FileProperty;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.model.domain.FileAttView;
import uk.ac.ebi.biostd.model.domain.PagedFile;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;
import uk.ac.ebi.biostd.rest.dto.FilePagingResponse;
import uk.ac.ebi.biostd.rest.mappers.FileMapper;
import uk.ac.ebi.biostd.services.files.FileService;
import uk.ac.ebi.biostd.services.files.PagingService;
import uk.ac.ebi.biostd.services.files.meta.MetadataService;


/**
 * Contains file related end points.
 *
 * @author Juan Rada
 */
@RestController
@AllArgsConstructor
public class FilesRestResource {

    private final PagingService pagingService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("submissions/{accNo}/files")
    public PagedFile getFiles(
            @PathVariable("accNo") String accNo,
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "size", required = false) int size,
            @RequestParam(name = "filter", required = false) String filter)    {
        PagedFile resultFiles = pagingService.getRelatedFiles(accNo, page, size, filter);
        return resultFiles;
    }

}
