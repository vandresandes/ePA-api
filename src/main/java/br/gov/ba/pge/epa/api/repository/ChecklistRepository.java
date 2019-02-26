package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.repository.query.ChecklistRepositoryQuery;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>, JpaSpecificationExecutor<Checklist>, ChecklistRepositoryQuery {

}
