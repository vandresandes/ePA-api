package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.controller.specification.DocumentoSpecification;
import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.repository.DocumentoRepository;
import br.gov.ba.pge.epa.api.repository.filter.DocumentoFilter;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private DocumentoRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Documento> save(@Valid @RequestBody Documento entity, HttpServletResponse response) {
		Documento savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> findById(@PathVariable Long id) {
		Optional<Documento> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/all")
	public List<Documento> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<Documento> filtrar(DocumentoFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping({ "/nomes" })
	public List<String> buscarNomes(DocumentoFilter filter) {
		return repository.buscarNomes(filter);
	}

	@GetMapping
	public Page<Documento> buscarPaginado(DocumentoFilter filter, Pageable pageable) {
		Specification<Documento> specification = DocumentoSpecification.buscar(filter);
	    return repository.findAll(specification, pageable);
	}

}
