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

import br.gov.ba.pge.epa.api.controller.specification.TermoEspecificoSpecification;
import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.repository.TermoEspecificoRepository;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;

@RestController
@RequestMapping("/termoespecifico")
public class TermoEspecificoController {

	@Autowired
	private TermoEspecificoRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<TermoEspecifico> save(@Valid @RequestBody TermoEspecifico entity, HttpServletResponse response) {
		TermoEspecifico savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TermoEspecifico> findById(@PathVariable Long id) {
		Optional<TermoEspecifico> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<TermoEspecifico> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<TermoEspecifico> filtrar(TermoEspecificoFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping({ "/filtrar/nomes" })
	public List<String> buscarNomes(TermoEspecificoFilter filter) {
		return repository.buscarNomes(filter);
	}

	@GetMapping({"/buscarpaginado"})
	public Page<TermoEspecifico> buscarPaginado(
			TermoEspecificoFilter filter,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		Specification<TermoEspecifico> specification = TermoEspecificoSpecification.buscar(filter);
		
	    PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "nome");
	    Page<TermoEspecifico> resultados = repository.findAll(specification, pageable);
	    return resultados;
	}

}
