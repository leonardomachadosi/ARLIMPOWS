package br.ufma.lsdi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticulateMatterType implements Serializable {

    private Long id;
    private String name;
}
