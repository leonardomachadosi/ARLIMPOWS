package br.ufma.lsdi.repository;

import br.ufma.lsdi.model.domain.interscity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
