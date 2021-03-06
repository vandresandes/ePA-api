package br.gov.ba.pge.epa.api.repository.query;

import java.util.List;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;

public interface TermoGeralRepositoryQuery {

	public List<TermoGeral> filtrar(TermoGeralFilter filter);
	
	public List<String> buscarNomes(TermoGeralFilter filter);
}
