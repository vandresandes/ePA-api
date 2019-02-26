package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Orgao;
import br.gov.ba.pge.epa.api.repository.query.OrgaoRepositoryQuery;

public interface OrgaoRepository extends JpaRepository<Orgao, Long>, JpaSpecificationExecutor<Orgao>, OrgaoRepositoryQuery {

}
