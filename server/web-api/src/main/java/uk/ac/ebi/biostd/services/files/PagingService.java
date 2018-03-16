package uk.ac.ebi.biostd.services.files;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.biostd.model.domain.File;
import uk.ac.ebi.biostd.model.domain.PagedFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@Getter
@Setter
public class PagingService
{
    @Autowired
    private EntityManager entityManager;

    public PagedFile getRelatedFiles(String accession, int page, int size, String filter){
        Query countQuery = entityManager.createNamedQuery("File.count");
        countQuery.setParameter("accNo", accession);
        countQuery.setParameter("filter", "%"+filter+"%");
        long totalPage = (long)countQuery.getSingleResult()/size + 1;
        Query queryAllFiles = entityManager.createNamedQuery("File.getFileAttByAccession", File.class);
        queryAllFiles.setParameter("accNo", accession);
        queryAllFiles.setParameter("filter", "%"+filter+"%");
        List<File> fileList = queryAllFiles.setFirstResult(page*size).setMaxResults(size).getResultList();
        PagedFile pagedFile = new PagedFile();
        pagedFile.setCurentPage(page);
        pagedFile.setPageSize(size);
        pagedFile.setFiles(fileList);
        pagedFile.setTotalPage(totalPage);
        return pagedFile;
    }
}
