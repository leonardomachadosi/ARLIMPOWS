package br.ufma.lsdi.modelAcess.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CG18_MOTIVO_BLOQUEIO")
@SequenceGenerator(
        name = "CG18_MOTIVO_BLOQUEIO_CG18_COD_MOTIVO_BLOQUEIO_SEQ",
        sequenceName = "CG18_MOTIVO_BLOQUEIO_CG18_COD_MOTIVO_BLOQUEIO_SEQ",
        allocationSize = 1,
        initialValue = 1
)
public class MotivoBloqueio implements Serializable {

    private static final long serialVersionUID = 4944771745834713870L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "CG18_MOTIVO_BLOQUEIO_CG18_COD_MOTIVO_BLOQUEIO_SEQ"
    )
    @Column(name = "CG18_COD_MOTIVO_BLOQUEIO")
    private Long id;

    @Column(name = "CG16_DESCRICAO")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotivoBloqueio that = (MotivoBloqueio) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return descricao != null ? descricao.equals(that.descricao) : that.descricao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MotivoBloqueio{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
