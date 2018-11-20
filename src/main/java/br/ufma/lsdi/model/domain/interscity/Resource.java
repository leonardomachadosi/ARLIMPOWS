package br.ufma.lsdi.model.domain.interscity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "AL01_RECURSO")
@SequenceGenerator(sequenceName = "AL01_RECURSO_SEQ", name = "AL01_RECURSO_SEQ", allocationSize = 1)
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AL01_RECURSO_SEQ")
    @Column(name = "AL01_cod_recurso")
    private Integer id;
    @Column(name = "AL01_URI")
    private Integer uri;
    @Column(name = "AL01_CREATED_AT")
    private String createdAt;
    @Column(name = "AL01_UPDATED_AT")
    private String updatedAt;
    @Column(name = "AL01_LAT")
    private Double lat;
    @Column(name = "AL01_LON")
    private Double lon;
    @Column(name = "AL01_STATUS")
    private String status;
    @Column(name = "AL01_COLLECT_INTERVAL")
    private String collectInterval;
    @Column(name = "AL01_DESCRIPTION")
    private String description;
    @Column(name = "AL01_UUID")
    private String uuid;
    @Column(name = "AL01_CITY")
    private String city;
    @Column(name = "AL01_NEIGHBORHOOD")
    private String neighborhood;
    @Column(name = "AL01_STATE")
    private String state;
    @Column(name = "AL01_POSTAL_CODE")
    private String postalCode;
    @Column(name = "AL01_COUNTRY")
    private String country;
    @Transient
    private List<String> capabilities;

}
