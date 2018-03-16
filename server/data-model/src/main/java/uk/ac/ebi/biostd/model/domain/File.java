package uk.ac.ebi.biostd.model.domain;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.cdi.Eager;

/**
 * Represent a submission file.
 */

@XmlRootElement()
@Getter
@Setter
@Entity
@Table(name = "FileRef")
//@NamedEntityGraph(name = "attributes", attributeNodes = @NamedAttributeNode("attributes"))
@NamedQueries({@NamedQuery(name="File.getFileAttByAccession", query = "select f from File f where f.section.submission.accNo=:accNo and (f.name Like :filter or f.id = any (select att.file_id from FileAttribute att where att.name like :filter or att.value like :filter)) "),
               @NamedQuery(name="File.count", query = "select count (1) from File f where f.section.submission.accNo=:accNo and (f.name Like :filter or f.id = any (select att.file_id from FileAttribute att where att.name like :filter or att.value like :filter)) ")
                })
public class File {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @JsonIgnore
    @Column(updatable = false, insertable = false)
    private long sectionId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderColumn(name = "ord")
    private List<FileAttribute> attributes;

    @Column
    private int size;

    @Column(name = "ord")
    private int order;

    @Column()
    private String path;

    @Transient
    public String getFullPath() {
        return StringUtils.defaultString(path).concat(section.getSubmission().getPath()).concat(name);
    }

}
