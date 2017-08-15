package uk.ac.ebi.biostd.persistence.file.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.persistence.common.filtering.Filter;

@Getter
@AllArgsConstructor
public class FileFilter {

    private final Filter filter;
    private final QFile file;
}
