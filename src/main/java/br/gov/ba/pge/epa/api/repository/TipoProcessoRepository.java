package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.query.TipoProcessoRepositoryQuery;

public interface TipoProcessoRepository extends JpaRepository<TipoProcesso, Long>, JpaSpecificationExecutor<TipoProcesso>, TipoProcessoRepositoryQuery {

}
