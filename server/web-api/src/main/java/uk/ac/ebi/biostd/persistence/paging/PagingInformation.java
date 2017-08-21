package uk.ac.ebi.biostd.persistence.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PagingInformation {

    private int page;
    private int size;
}
