package br.ufma.lsdi.service.client;

import br.ufma.lsdi.model.domain.Catalog;
import br.ufma.lsdi.model.domain.auxiliar.ResourceAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceDataAuxiliar;
import br.ufma.lsdi.model.domain.auxiliar.ResourceHelper;
import br.ufma.lsdi.service.interscity.ResourceClient;
import org.springframework.stereotype.Component;

@Component
public class ResourceClientFallback implements ResourceClient {
    @Override
    public ResourceAuxiliar getResource() {
        return new ResourceAuxiliar();
    }

    @Override
    public ResourceDataAuxiliar saveResource(ResourceDataAuxiliar data) {
        return new ResourceDataAuxiliar();
    }

    @Override
    public ResourceDataAuxiliar getResourceByUuid(String uuid) {
        return new ResourceDataAuxiliar();
    }

    @Override
    public ResourceHelper getAllData(Catalog catalog) {
        return new ResourceHelper();
    }

    @Override
    public ResourceAuxiliar getAllResourceByCapability() {
        return new ResourceAuxiliar();
    }

    @Override
    public ResourceHelper getLastData(Catalog catalog) {
        return new ResourceHelper();
    }

    @Override
    public ResourceAuxiliar getAllResourceSensor() {
        return new ResourceAuxiliar();
    }


}
