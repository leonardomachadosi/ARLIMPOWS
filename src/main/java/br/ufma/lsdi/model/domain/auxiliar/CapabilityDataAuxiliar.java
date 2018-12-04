package br.ufma.lsdi.model.domain.auxiliar;

import br.ufma.lsdi.model.domain.interscity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapabilityDataAuxiliar implements Serializable {

    private Double value;
    private String timestamp;
    private Double lat;
    private Double lon;
    private Resource resource;

}
