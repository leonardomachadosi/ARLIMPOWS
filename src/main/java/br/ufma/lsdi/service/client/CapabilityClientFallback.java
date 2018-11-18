package br.ufma.lsdi.service.client;

import br.ufma.lsdi.model.domain.auxiliar.CapabilityAuxiliar;
import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.service.interscity.CapabilityClient;
import org.springframework.stereotype.Component;

@Component
public class CapabilityClientFallback implements CapabilityClient {


    @Override
    public CapabilityAuxiliar getCapability() {
        return new CapabilityAuxiliar();
    }

    @Override
    public Capability saveCapability(Capability capability) {
        return new Capability();
    }
}
