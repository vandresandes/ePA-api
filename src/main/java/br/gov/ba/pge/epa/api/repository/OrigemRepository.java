package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.origem.OrigemRepositoryQuery;

public interface OrigemRepository extends JpaRepository<Origem, Long>, JpaSpecificationExecutor<Origem>, OrigemRepositoryQuery {

}
