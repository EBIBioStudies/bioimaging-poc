package uk.ac.ebi.biostd.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Represent a section inside submissions.
 */
@Entity
@Getter
@Setter
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(updatable = false, insertable = false, name = "submission_id")
    private long submissionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "submission_id")
    private Submission submission;

    @Column
    private String type;
}
