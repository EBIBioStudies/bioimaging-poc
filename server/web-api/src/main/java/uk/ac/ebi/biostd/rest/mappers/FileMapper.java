package uk.ac.ebi.biostd.rest.mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import uk.ac.ebi.biostd.model.data.FileProperty;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.persistence.paging.DataPage;
import uk.ac.ebi.biostd.rest.dto.AttributeValueDTO;
import uk.ac.ebi.biostd.rest.dto.FileDTO;
import uk.ac.ebi.biostd.rest.dto.FilePagingResponse;

@Component
public class FileMapper {

    public FilePagingResponse getFilePagingResponse(DataPage<File> files, Map<String, FileProperty> propertyMap) {
        return new FilePagingResponse(
                files.getTotal(),
                files.getTotalPages(),
                files.getPageNumber(),
                files.getPageSize(),
                getFileDTOList(files.getElements(), propertyMap));
    }

    private List<FileDTO> getFileDTOList(List<File> files, Map<String, FileProperty> metadata) {
        return files.stream().map(file -> getFileDTO(file, metadata)).collect(Collectors.toList());
    }

    private FileDTO getFileDTO(File file, Map<String, FileProperty> metadata) {
        List<AttributeValueDTO> attributeValueDTOList =
                file.getAttributes().stream()
                        .map(attribute -> new AttributeValueDTO(metadata.get(attribute.getName()).getId(),
                                attribute.getValue()))
                        .collect(Collectors.toList());

        return FileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .size(file.getSize())
                .path(file.getPath())
                .attributes(attributeValueDTOList).build();
    }
}
