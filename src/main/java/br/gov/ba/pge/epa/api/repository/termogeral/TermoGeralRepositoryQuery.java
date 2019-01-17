package br.gov.ba.pge.epa.api.repository.termogeral;

import java.util.List;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;

public interface TermoGeralRepositoryQuery {

	public List<TermoGeral> filtrar(TermoGeralFilter filter);
}
