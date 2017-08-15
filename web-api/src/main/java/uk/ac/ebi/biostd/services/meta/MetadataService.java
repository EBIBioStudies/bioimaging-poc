package uk.ac.ebi.biostd.services.meta;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biostd.model.data.FileProperty;
import uk.ac.ebi.biostd.model.data.PropertyType;
import uk.ac.ebi.biostd.model.domain.FileAttribute;
import uk.ac.ebi.biostd.persistence.repositories.FileAttributeRepository;

@Service
@AllArgsConstructor
public class MetadataService {

    private final FileAttributeRepository attributeRepository;

    @Cacheable("sub_metadata")
    public QueryMetadata getMetadata(long submissionId) {
        Stream<FileProperty> properties = getProperties(submissionId).stream();

        Map<String, PropertyType> typeMap = properties.collect(toMap(FileProperty::getId, FileProperty::getType));
        Map<String, String> map = properties.collect(toMap(FileProperty::getId, FileProperty::getName));
        return new QueryMetadata(typeMap, map);
    }

    @Cacheable("sub_properties")
    public List<FileProperty> getProperties(long submissionId) {
        List<FileAttribute> attributes = attributeRepository.findByFileSectionSubmissionIdAndGroupByName(submissionId);
        List<FileProperty> fileProperties = attributes.stream().map(attr ->
                new FileProperty(getId(attr.getName()), attr.getName(), PropertyType.ATTRIBUTE)).collect(toList());

        fileProperties.add(0, new FileProperty("name", "Name", PropertyType.FILE));
        fileProperties.add(1, new FileProperty("size", "Size", PropertyType.FILE));
        fileProperties.add(2, new FileProperty("order", "Order", PropertyType.FILE));
        return fileProperties;
    }

    public Map<String, FileProperty> getPropertiesMap(long submissionId) {
        return getProperties(submissionId).stream().collect(toMap(f -> f.getName(), f -> f));
    }

    private String getId(String attributeName) {
        String id = attributeName.trim().replaceAll("\\s", "_");
        return id.toLowerCase();
    }
}
