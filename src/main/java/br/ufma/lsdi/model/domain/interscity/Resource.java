package br.ufma.lsdi.model.domain.interscity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource implements Serializable {
    private Integer id;
    private Integer uri;
    private String createdAt;
    private String updatedAt;
    private Double lat;
    private Double lon;
    private String status;
    private String collectInterval;
    private String description;
    private String uuid;
    private String city;
    private String neighborhood;
    private String state;
    private String postalCode;
    private String country;
    private List<String> capabilities;

}
