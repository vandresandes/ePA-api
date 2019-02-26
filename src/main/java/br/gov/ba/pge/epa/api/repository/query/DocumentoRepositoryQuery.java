package br.gov.ba.pge.epa.api.repository.query;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.repository.filter.DocumentoFilter;

public interface DocumentoRepositoryQuery {

	public List<Documento> filtrar(DocumentoFilter filter);
	
	public List<String> buscarNomes(DocumentoFilter filter);
}
