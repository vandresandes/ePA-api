package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.TermoGeral;

public interface TermoGeralRepository extends JpaRepository<TermoGeral, Long>, JpaSpecificationExecutor<TermoGeral> {

}
