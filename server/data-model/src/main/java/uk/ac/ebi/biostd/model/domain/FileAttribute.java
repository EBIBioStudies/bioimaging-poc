package uk.ac.ebi.biostd.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represent a configurable file attribute.
 */
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileAttribute {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "file_id")
    private File file;

    @JsonIgnore
    @Column(name = "file_id", updatable = false, insertable = false)
    private long file_id;

    @Column
    private String name;

    @Column
    private String value;

    @Column(name = "ord")
    private int order;
}
