package br.gov.ba.pge.epa.api.repository.termoEspecifico;

import java.util.List;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;

public interface TermoEspecificoRepositoryQuery {

	public List<TermoEspecifico> filtrar(TermoEspecificoFilter filter);
	
	public List<String> findAllNomes();
}
