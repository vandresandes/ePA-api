package br.gov.ba.pge.epa.api.repository.query;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Orgao;
import br.gov.ba.pge.epa.api.repository.filter.OrgaoFilter;

public interface OrgaoRepositoryQuery {

	public List<Orgao> filtrar(OrgaoFilter filter);
}
