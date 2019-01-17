package br.gov.ba.pge.epa.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.TipoProcessoRepository;
import br.gov.ba.pge.epa.api.repository.filter.TipoProcessoFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tipoprocesso")
public class TipoProcessoController {

	@Autowired
	private TipoProcessoRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<TipoProcesso> save(@Valid @RequestBody TipoProcesso entity, HttpServletResponse response) {
		TipoProcesso savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoProcesso> findById(@PathVariable Long id) {
		Optional<TipoProcesso> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<TipoProcesso> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@GetMapping("/filtrar")
	public List<TipoProcesso> filtrar(TipoProcessoFilter filter) {
		return repository.filtrar(filter);
	}

	@GetMapping({ "/filtrar/nomes" })
	public List<String> buscarNomes(TipoProcessoFilter filter) {
		return repository.buscarNomes(filter);
	}

	@GetMapping({ "/buscarpaginado" })
	public Page<TipoProcesso> buscarPaginado(@RequestParam("nome") Optional<String> nome,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		Specification<TipoProcesso> specification = new Specification<TipoProcesso>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TipoProcesso> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				if (nome.isPresent() && EPAUtil.isNotBlank(nome.get())) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.get().toLowerCase() + "%");
				}
				return null;
			}
		};

		PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "nome");
		Page<TipoProcesso> resultados = repository.findAll(specification, pageable);
		return resultados;
	}

}
