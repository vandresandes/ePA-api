package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.gov.ba.pge.epa.api.model.TermoGeral;

public interface TermoGeralRepository extends JpaRepository<TermoGeral, Long>, PagingAndSortingRepository<TermoGeral, Long>, JpaSpecificationExecutor<TermoGeral> {

}
