package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.controller.specification.ChecklistSpecification;
import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.repository.ChecklistRepository;
import br.gov.ba.pge.epa.api.repository.filter.ChecklistFilter;

@RestController
@RequestMapping("/checklist")
public class ChecklistController {

	@Autowired
	private ChecklistRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Checklist> save(@Valid @RequestBody Checklist entity, HttpServletResponse response) {
		Checklist savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Checklist> findById(@PathVariable Long id) {
		Optional<Checklist> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Checklist> findAll() {
		return repository.findAll(Sort.by("id"));
	}

	@GetMapping("/filtrar")
	public List<Checklist> filtrar(ChecklistFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping({ "/buscarpaginado" })
	public Page<Checklist> buscarPaginado(
			ChecklistFilter filter, 
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		
		Specification<Checklist> specification = ChecklistSpecification.filtrar(filter);

		PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "id");
		Page<Checklist> resultados = repository.findAll(specification, pageable);
		return resultados;
	}

}
