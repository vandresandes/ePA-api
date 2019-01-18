package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.OrigemRepository;
import br.gov.ba.pge.epa.api.repository.filter.OrigemFilter;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/origem")
public class OrigemController {

	@Autowired
	private OrigemRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<Origem> findById(@PathVariable Long id) {
		Optional<Origem> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Origem> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<Origem> filtrar(OrigemFilter filter) {
		return repository.filtrar(filter);
	}

}
