package uk.ac.ebi.biostd.services.meta;

import java.util.Map;
import lombok.AllArgsConstructor;
import uk.ac.ebi.biostd.model.data.PropertyType;

@AllArgsConstructor
public class QueryMetadata {

    private final Map<String, PropertyType> propertyTypeMap;
    private final Map<String, String> propertyIdToNameMap;

    public boolean isFileProp(String propertyName) {
        return propertyTypeMap.get(propertyName) == PropertyType.FILE;
    }

    public String getName(String propertyId) {
        return propertyIdToNameMap.get(propertyId);
    }
}
