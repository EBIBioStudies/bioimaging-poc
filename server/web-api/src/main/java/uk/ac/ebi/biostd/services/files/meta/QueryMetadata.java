package uk.ac.ebi.biostd.services.files.meta;

import java.util.Map;
import lombok.AllArgsConstructor;
import uk.ac.ebi.biostd.model.data.PropertyType;

@AllArgsConstructor
public class QueryMetadata {

    private final Map<String, PropertyType> propertyTypeMap;
    private final Map<String, String> propertyIdToNameMap;

    /**
     * Return true if given property is a file property, otherwise false.
     *
     * @param propertyName the property name
     * @return true if given property is a file property, otherwise false.
     */
    public boolean isFileProp(String propertyName) {
        return propertyTypeMap.get(propertyName) == PropertyType.FILE;
    }

    /**
     * Obtain the property name for the given id
     *
     * @param propertyId the property id.
     * @return an String name of
     */
    public String getName(String propertyId) {
        return propertyIdToNameMap.get(propertyId);
    }
}
