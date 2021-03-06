package br.gov.ba.pge.epa.api.repository.query;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.repository.filter.NucleoFilter;

public interface NucleoRepositoryQuery {

	public List<Nucleo> filtrar(NucleoFilter filter);

	public List<String> buscarNomes(NucleoFilter filter);
}
