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

import br.gov.ba.pge.epa.api.controller.specification.OrgaoSpecification;
import br.gov.ba.pge.epa.api.model.Orgao;
import br.gov.ba.pge.epa.api.repository.OrgaoRepository;
import br.gov.ba.pge.epa.api.repository.filter.OrgaoFilter;

@RestController
@RequestMapping("/orgao")
public class OrgaoController {

	@Autowired
	private OrgaoRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<Orgao> findById(@PathVariable Long id) {
		Optional<Orgao> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public List<Orgao> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<Orgao> filtrar(OrgaoFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping
	public Page<Orgao> buscarPaginado(OrgaoFilter filter, Pageable pageable) {
		Specification<Orgao> specification = OrgaoSpecification.buscar(filter);
	    return repository.findAll(specification, pageable);
	}

}
