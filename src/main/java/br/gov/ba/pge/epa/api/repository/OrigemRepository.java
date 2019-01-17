package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Origem;

public interface OrigemRepository extends JpaRepository<Origem, Long>, JpaSpecificationExecutor<Origem> {

}
