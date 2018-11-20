package br.ufma.lsdi.model.domain.interscity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Entity
@Table(name = "AL02_CAPACIDADE")
@SequenceGenerator(sequenceName = "AL02_CAPACIDADE_SEQ", name = "AL02_CAPACIDADE_SEQ", allocationSize = 1)
public class Capability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AL02_CAPACIDADE_SEQ")
    @Column(name = "AL02_COD_CAPACIDADE")
    private Long id;
    @Column(name = "AL02_NAME")
    private String name;
    @Column(name = "AL02_FUNCTION")
    private int function;
    @Column(name = "AL02_DESCRIPTION")
    private String description;
    @Column(name = "AL02_CAPABILITYTYPE")
    private String capabilityType;
    @Column(name = "AL02_LATITUDE")
    private Double lat;
    @Column(name = "AL02_LONGITUDE")
    private Double lon;

}
