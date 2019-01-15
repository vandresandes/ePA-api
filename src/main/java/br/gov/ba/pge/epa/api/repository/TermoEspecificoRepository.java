package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;

public interface TermoEspecificoRepository extends JpaRepository<TermoEspecifico, Long>, JpaSpecificationExecutor<TermoEspecifico> {

}
