package br.ufma.lsdi.model.domain.interscity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class Capability implements Serializable {

    private Long id;
    private String name;
    private int function;
    private String description;
    private String capabilityType;

}
