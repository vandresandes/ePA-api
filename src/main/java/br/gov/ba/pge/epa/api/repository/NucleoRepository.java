package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.repository.nucleo.NucleoRepositoryQuery;

public interface NucleoRepository extends JpaRepository<Nucleo, Long>, JpaSpecificationExecutor<Nucleo>, NucleoRepositoryQuery {

}
