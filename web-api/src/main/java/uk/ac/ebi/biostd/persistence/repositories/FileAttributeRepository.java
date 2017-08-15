package uk.ac.ebi.biostd.persistence.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import uk.ac.ebi.biostd.model.domain.FileAttribute;
import uk.ac.ebi.biostd.persistence.common.custom.CustomRepository;

/**
 * File attributes repository, contains {@link FileAttribute} persistence operations.
 */
public interface FileAttributeRepository extends CustomRepository<FileAttribute, Long> {

    /**
     * Obtain the list of file attributes referenced by a given submission section.
     *
     * @param submissionId a list of {@link FileAttribute} that correspond to file attributes list
     */
    @Query("SELECT fa FROM FileAttribute fa, File f, Section sec where fa.file = f and f.section = sec "
            + "and sec.submissionId = ?1 group by fa.name")
    List<FileAttribute> findByFileSectionSubmissionIdAndGroupByName(long submissionId);

    /**
     * Search the list of attributes that match the givens ids.
     *
     * @param ids list of attributes identifiers
     * @return a list of {@link FileAttribute} with file attribute information
     */
    List<FileAttribute> findByFileIdIn(List<Long> ids);

}
