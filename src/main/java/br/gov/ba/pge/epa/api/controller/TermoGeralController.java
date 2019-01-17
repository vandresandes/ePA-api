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
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.repository.TermoGeralRepository;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;
import br.gov.ba.pge.epa.api.util.EPAUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/termogeral")
public class TermoGeralController {

	@Autowired
	private TermoGeralRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	public List<TermoGeral> findAll() {
		return repository.findAll(Sort.by("nome"));
	}

	@PostMapping
	public ResponseEntity<TermoGeral> save(@Valid @RequestBody TermoGeral entity, HttpServletResponse response) {
		TermoGeral savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TermoGeral> findById(@PathVariable Long id) {
		Optional<TermoGeral> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@GetMapping({"/buscarpaginado"})
	public Page<TermoGeral> buscarPaginado(
			@RequestParam("nome") Optional<String> nome,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		Specification<TermoGeral> specification = new Specification<TermoGeral>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TermoGeral> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (nome.isPresent() && EPAUtil.isNotBlank(nome.get())) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.get().toLowerCase() + "%");
				}
				return null;
			}
		}; 

	    PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "nome");
	    Page<TermoGeral> resultados = repository.findAll(specification, pageable);
	    return resultados;
	}

	@GetMapping({"/buscar"})
	public List<TermoGeral> buscar(@RequestParam("nome") Optional<String> nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TermoGeral> cq = cb.createQuery(TermoGeral.class);
		Root<TermoGeral> root = cq.from(TermoGeral.class);

		Predicate[] predicates = extractPredicates(cb, root, nome);
		cq.select(cq.getSelection()).where(predicates);
		cq.orderBy(cb.asc(root.get("nome")));

		TypedQuery<TermoGeral> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@GetMapping({ "/buscar/nomes", "/buscar/nomes/{nome}" })
	public List<String> buscarNomes(@PathVariable Optional<String> nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<TermoGeral> root = cq.from(TermoGeral.class);

		Predicate[] predicates = extractPredicates(cb, root, nome);
		cq.select(root.get("nome")).where(predicates);
		cq.orderBy(cb.asc(root.get("nome")));

		TypedQuery<String> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	private Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, Optional<String> nome) {
		List<Predicate> predicates = new ArrayList<>();
		if (nome.isPresent() && EPAUtil.isNotBlank(nome.get())) {
			predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.get().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[] {});
	}

	@GetMapping("/filtrar")
	public List<TermoGeral> filtrar(TermoGeralFilter filter) {
		return repository.filtrar(filter);
	}

}
