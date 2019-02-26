package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.repository.query.DocumentoRepositoryQuery;

public interface DocumentoRepository extends JpaRepository<Documento, Long>, JpaSpecificationExecutor<Documento>, DocumentoRepositoryQuery {

}
