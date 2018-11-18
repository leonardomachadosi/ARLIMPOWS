package br.ufma.lsdi.model.domain.auxiliar;

import java.io.Serializable;

public class ResourceDataAuxiliar implements Serializable {
    private ResourceData data;

    public ResourceData getData() {
        return data;
    }

    public void setData(ResourceData data) {
        this.data = data;
    }
}
