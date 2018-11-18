package br.ufma.lsdi.model.domain.auxiliar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapabilityDataAuxiliar implements Serializable {

    private String balneabilidade;
    private String timestamp;
}