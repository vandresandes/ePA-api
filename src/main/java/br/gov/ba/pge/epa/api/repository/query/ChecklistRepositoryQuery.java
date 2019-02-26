package br.gov.ba.pge.epa.api.repository.query;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.repository.filter.ChecklistFilter;

public interface ChecklistRepositoryQuery {

	public List<Checklist> filtrar(ChecklistFilter filter);
}
