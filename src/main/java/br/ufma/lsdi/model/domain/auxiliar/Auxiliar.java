package br.ufma.lsdi.model.domain.auxiliar;

import br.ufma.lsdi.model.domain.interscity.Resource;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Auxiliar implements Serializable {

    private Resource recurso;
    private List<CapabilityDataAuxiliar> capacidades;
}
