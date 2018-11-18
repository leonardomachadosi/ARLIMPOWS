package br.ufma.lsdi.model.domain.auxiliar;

import br.ufma.lsdi.model.domain.interscity.Capability;
import lombok.Data;

import java.util.List;

@Data
public class CapabilityAuxiliar {

    private List<Capability> capabilities;
}
