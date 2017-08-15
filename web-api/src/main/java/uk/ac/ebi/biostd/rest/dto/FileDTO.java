package uk.ac.ebi.biostd.rest.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import uk.ac.ebi.biostd.rest.json.AttributeSerializer;

@Getter
@Builder
@JsonSerialize(using = AttributeSerializer.class)
public class FileDTO {

    private final long id;
    private final String name;
    private final int size;
    private final String path;
    private final long sectionId;

    private final List<AttributeValueDTO> attributes;
}
