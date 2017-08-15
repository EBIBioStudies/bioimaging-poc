package uk.ac.ebi.biostd.model.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Represent a submission file.
 */
@Getter
@Setter
@Entity
@Table(name = "FileRef")
@NamedEntityGraph(name = "attributes", attributeNodes = @NamedAttributeNode("attributes"))
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(updatable = false, insertable = false)
    private long sectionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sectionId")
    private Section section;

    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
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
