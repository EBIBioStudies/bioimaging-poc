package uk.ac.ebi.biostd.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represent a file data-grid column.
 */
@Data
@AllArgsConstructor
public class FileProperty {

    private final String id;
    private final String name;
    private final PropertyType type;
    private final boolean visible;
}
