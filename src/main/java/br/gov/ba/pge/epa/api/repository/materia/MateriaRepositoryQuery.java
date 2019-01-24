package br.gov.ba.pge.epa.api.repository.materia;

import java.util.List;

import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.repository.filter.MateriaFilter;

public interface MateriaRepositoryQuery {

	public List<Materia> filtrar(MateriaFilter filter);
	
	public List<String> buscarNomes(MateriaFilter filter);
}
