package uk.ac.ebi.biostd.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedFile {
    private int curentPage;
    private int pageSize;
    private long totalPage;
    private  String type;
    List<File> files;
}
