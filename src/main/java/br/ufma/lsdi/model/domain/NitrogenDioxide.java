package br.ufma.lsdi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NitrogenDioxide implements Serializable {

    private Long id;
    private String name;
    private Double value;
    private Double longitude;
    private Double latitude;
    private String timestamp;

}
