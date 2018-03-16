package uk.ac.ebi.biostd.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.model.domain.FileAttView;
import uk.ac.ebi.biostd.persistence.common.custom.CustomRepository;

import java.util.List;

/**
 * Contains {@link File} persistence operations.
 */
public interface FilesRepository extends CustomRepository<FileAttView, Long> {

    @Query("select f from File f where f.section.submission.accNo=:accNo")
    List<File> findFilesByAccNo(String accNo);
}
