package br.gov.ba.pge.epa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.repository.NucleoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/nucleo")
public class NucleoController {

	@Autowired
	private NucleoRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	public List<Nucleo> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@PostMapping
	public ResponseEntity<Nucleo> save(@Valid @RequestBody Nucleo entity, HttpServletResponse response) {
		Nucleo savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Nucleo> findById(@PathVariable Long id) {
		Optional<Nucleo> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping({ "/buscar", "/buscar/{nome}" })
	public List<Nucleo> buscar(@PathVariable Optional<String> nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Nucleo> cq = cb.createQuery(Nucleo.class);
		Root<Nucleo> root = cq.from(Nucleo.class);

		Predicate[] predicates = extractPredicates(new Nucleo(nome.isPresent() ? nome.get() : null), cb, root);
		cq.select(cq.getSelection()).where(predicates);

		TypedQuery<Nucleo> query = entityManager.createQuery(cq);
//	    query.setFirstResult(0);
//	    query.setMaxResults(0);
		return query.getResultList();
	}

	private Predicate[] extractPredicates(Nucleo filtro, CriteriaBuilder cb, Root<?> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filtro != null) {
			if (StringUtils.isNotBlank(filtro.getNome())) {
				predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
			}
		}
		return predicates.toArray(new Predicate[] {});
	}
}
