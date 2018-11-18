package br.ufma.lsdi.model.domain.auxiliar;

import br.ufma.lsdi.model.domain.interscity.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceAuxiliar implements Serializable {

    private List<Resource> resources;
}
