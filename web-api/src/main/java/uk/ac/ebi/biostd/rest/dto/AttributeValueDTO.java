package uk.ac.ebi.biostd.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AttributeValueDTO {

    private String name;
    private String value;
}
