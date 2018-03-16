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
import org.hibernate.annotations.Immutable;

/**
 * Represent a submission file.
 */
@Getter
@Setter
@Entity
@Table(name = "FileAttView")
//@NamedEntityGraph(name = "attributes", attributeNodes = @NamedAttributeNode("attributes"))
public class FileAttView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private long submission_id;

    @Column
    private String nameQualifierString;

    @Column
    private Integer ord;

    @Column
    private Long size;

    @Column(name = "accNo")
    private String accNo;

    @Column(name = "columnKey")
    private String columnKey;

//    @OneToMany(mappedBy = "file", cascade = CascadeType.ALL)
//    @OrderColumn(name = "ord")
//    private List<FileAttribute> attributes;


}
