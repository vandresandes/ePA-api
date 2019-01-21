package br.gov.ba.pge.epa.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.repository.MateriaRepository;
import br.gov.ba.pge.epa.api.repository.filter.MateriaFilter;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private MateriaRepository repository;

	@GetMapping
	public List<Materia> findAll() {
		List<Materia> lista = repository.findAll(Sort.by("nome"));
		return lista;
	}

	@GetMapping("/filtrar")
	public List<Materia> filtrar(MateriaFilter filter) {
		return repository.filtrar(filter);
	}
}
