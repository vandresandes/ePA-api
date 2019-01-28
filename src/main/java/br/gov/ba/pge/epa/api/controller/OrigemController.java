package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.controller.specification.OrigemSpecification;
import br.gov.ba.pge.epa.api.model.Origem;
import br.gov.ba.pge.epa.api.repository.OrigemRepository;
import br.gov.ba.pge.epa.api.repository.filter.OrigemFilter;

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

	@GetMapping("/all")
	public List<Origem> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<Origem> filtrar(OrigemFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping
	public Page<Origem> buscarPaginado(OrigemFilter filter, Pageable pageable) {
		Specification<Origem> specification = OrigemSpecification.buscar(filter);
	    return repository.findAll(specification, pageable);
	}

}
