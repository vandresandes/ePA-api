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

import br.gov.ba.pge.epa.api.controller.specification.TermoGeralSpecification;
import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.TermoGeralRepository;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;

@RestController
@RequestMapping("/termogeral")
public class TermoGeralController {

	@Autowired
	private TermoGeralRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;

	
	@PostMapping
	public ResponseEntity<TermoGeral> save(@Valid @RequestBody TermoGeral entity, HttpServletResponse response) {
		TermoGeral savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TermoGeral> findById(@PathVariable Long id) {
		Optional<TermoGeral> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<TermoGeral> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<TermoGeral> filtrar(TermoGeralFilter filter) {
		return repository.filtrar(filter);
	}
	
	@GetMapping({ "/filtrar/nomes" })
	public List<String> buscarNomes(TermoGeralFilter filter) {
		return repository.buscarNomes(filter);
	}

	@GetMapping({"/buscarpaginado"})
	public Page<TermoGeral> buscarPaginado(
			TermoGeralFilter filter,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		Specification<TermoGeral> specification = TermoGeralSpecification.buscar(filter);
		
	    PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "nome");
	    Page<TermoGeral> resultados = repository.findAll(specification, pageable);
	    return resultados;
	}

}
