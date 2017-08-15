package uk.ac.ebi.biostd.persistence.file.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uk.ac.ebi.biostd.model.domain.QFile;
import uk.ac.ebi.biostd.persistence.common.ordering.Order;

@Getter
@AllArgsConstructor
public class FileOrder {

    private final Order order;
    private final QFile file;
}
