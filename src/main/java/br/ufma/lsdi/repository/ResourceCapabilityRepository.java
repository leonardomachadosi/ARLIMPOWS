package br.ufma.lsdi.repository;

import br.ufma.lsdi.model.domain.interscity.Capability;
import br.ufma.lsdi.model.domain.interscity.ResourceCapability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceCapabilityRepository extends JpaRepository<ResourceCapability, Long> {

    @Query(value = "SELECT *" +
            "FROM AL03_RECURSO_CAPACIADADE " +
            "WHERE FKAL03AL02_COD_CAPACIDADE = ? ", nativeQuery = true)
    List<ResourceCapability> findAllByCapacidadeId(@Param("codigoCapacidade") Long codigoCapacidade);

}
