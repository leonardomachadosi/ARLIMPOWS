package br.ufma.lsdi.model.domain.interscity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "AL01_RECURSO")
@SequenceGenerator(sequenceName = "AL01_RECURSO_SEQ", name = "AL01_RECURSO_SEQ", allocationSize = 1)
public class ResourceCapability {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AL01_RECURSO_SEQ")
    @Column(name = "AL01_cod_recurso")
    private Long id;

    @Column(name = "AL03_DATA")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "FKAL03AL01_COD_RECURSO")
    private Resource recurso;

    @ManyToOne
    @JoinColumn(name = "FKAL03AL02_COD_CAPACIDADE")
    private Capability capacidade;

}
