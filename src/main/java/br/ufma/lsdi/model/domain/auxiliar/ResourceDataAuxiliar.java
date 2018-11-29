package br.ufma.lsdi.model.domain.auxiliar;

import br.ufma.lsdi.model.domain.interscity.Resource;

import java.io.Serializable;

public class ResourceDataAuxiliar implements Serializable {
    private Resource data;

    public Resource getData() {
        return data;
    }

    public void setData(Resource data) {
        this.data = data;
    }
}
