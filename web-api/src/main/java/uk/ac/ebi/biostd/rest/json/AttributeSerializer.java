package uk.ac.ebi.biostd.rest.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.rest.dto.AttributeValueDTO;
import uk.ac.ebi.biostd.rest.dto.FileDTO;

public class AttributeSerializer extends JsonSerializer<FileDTO> {

    @Override
    public void serialize(FileDTO value, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField(QFile.file.id.toString(), value.getId());
        jgen.writeStringField("name", value.getName());
        jgen.writeNumberField("size", value.getSize());
        jgen.writeStringField("path", value.getPath());
        jgen.writeNumberField("sectionId", value.getSectionId());
        writeAttributes(value, jgen);
        jgen.writeEndObject();
    }

    private void writeAttributes(FileDTO value, JsonGenerator jgen) throws IOException {
        for (AttributeValueDTO attr : value.getAttributes()) {
            jgen.writeStringField(attr.getName(), attr.getValue());
        }
    }
}
