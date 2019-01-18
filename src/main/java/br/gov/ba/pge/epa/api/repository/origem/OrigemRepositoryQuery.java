package br.gov.ba.pge.epa.api.repository.origem;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.filter.OrigemFilter;

public interface OrigemRepositoryQuery {

	public List<Origem> filtrar(OrigemFilter filter);
}
