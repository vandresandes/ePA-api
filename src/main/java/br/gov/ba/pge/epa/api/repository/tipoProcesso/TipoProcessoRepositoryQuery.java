package br.gov.ba.pge.epa.api.repository.tipoProcesso;

import java.util.List;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.filter.TipoProcessoFilter;

public interface TipoProcessoRepositoryQuery {

	public List<TipoProcesso> filtrar(TipoProcessoFilter filter);
	
	public List<String> buscarNomes(TipoProcessoFilter filter);
}
