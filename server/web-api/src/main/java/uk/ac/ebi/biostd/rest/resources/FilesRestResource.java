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
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.persistence.paging.PagingInformation;
import uk.ac.ebi.biostd.rest.dto.FilePagingResponse;
import uk.ac.ebi.biostd.rest.mappers.FileMapper;
import uk.ac.ebi.biostd.services.files.FileService;
import uk.ac.ebi.biostd.services.files.meta.MetadataService;


/**
 * Contains file related end points.
 *
 * @author Juan Rada
 */
@RestController
@AllArgsConstructor
public class FilesRestResource {

    private final FileService fileService;
    private final FileMapper fileMapper;
    private final MetadataService metadataService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("submissions/{accNo}/files")
    public FilePagingResponse getFiles(
            @PathVariable("accNo") String accNo,
            @RequestParam(name = "page", required = false) int page,
            @RequestParam(name = "size", required = false) int size,
            @RequestParam(name = "filterBy", required = false) Optional<String> filterBy,
            @RequestParam(name = "orderBy", required = false) Optional<String> orderBy) {

        DataPage<File> filePage = fileService
                .getFilePage(accNo, new PagingInformation(page, size), filterBy, orderBy);
        Map<String, FileProperty> metadata = metadataService.getPropertiesMap(accNo);

        return fileMapper.getFilePagingResponse(filePage, metadata);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("submissions/{accNo}/meta")
    public List<FileProperty> getSubmissionMetadata(@PathVariable("accNo") String accNo) {
        return metadataService.getProperties(accNo)
                .stream().filter(FileProperty::isVisible).collect(Collectors.toList());
    }
}
