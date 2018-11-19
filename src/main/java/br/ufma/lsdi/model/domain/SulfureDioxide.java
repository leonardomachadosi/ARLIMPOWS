package br.ufma.lsdi.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SulfureDioxide implements Serializable {

    private Long id;
    private Double value;
    private Double longitude;
    private Double latitude;
    private String timestamp;

}
