package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Materia> findById(@PathVariable Long id) {
		Optional<Materia> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Materia> findAll() {
		List<Materia> lista = repository.findAll(Sort.by("nome"));
		return lista;
	}

	@GetMapping("/filtrar")
	public List<Materia> filtrar(MateriaFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping({ "/filtrar/nomes" })
	public List<String> buscarNomes(MateriaFilter filter) {
		return repository.buscarNomes(filter);
	}

}
