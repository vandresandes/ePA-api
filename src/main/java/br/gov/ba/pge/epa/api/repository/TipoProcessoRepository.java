package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.TipoProcesso;

public interface TipoProcessoRepository extends JpaRepository<TipoProcesso, Long>, JpaSpecificationExecutor<TipoProcesso> {

}
