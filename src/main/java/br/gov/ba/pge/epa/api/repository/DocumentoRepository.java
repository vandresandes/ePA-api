package br.gov.ba.pge.epa.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ba.pge.epa.api.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
