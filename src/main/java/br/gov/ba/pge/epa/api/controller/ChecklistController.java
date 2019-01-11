package br.gov.ba.pge.epa.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.BooleanUtils;
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
import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.ChecklistRepository;
import br.gov.ba.pge.epa.api.util.EPAUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/checklist")
public class ChecklistController {

	@Autowired
	private ChecklistRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	public List<Checklist> findAll() {
		return repository.findAll(Sort.by("id"));
	}

	@PostMapping
	public ResponseEntity<Checklist> save(@Valid @RequestBody Checklist entity, HttpServletResponse response) {
		Checklist savedEntity = repository.save(entity);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedEntity.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Checklist> findById(@PathVariable Long id) {
		Optional<Checklist> optional = repository.findById(id);
		return optional.isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		repository.deleteById(id);
	}


	@GetMapping({ "/buscar" })
	public List<Checklist> buscar(
			@RequestParam("nucleo") Optional<String> nucleo, 
			@RequestParam("tipoProcesso") Optional<String> tipoProcesso,
			@RequestParam("termoGeral") Optional<String> termoGeral,
			@RequestParam("termoEspecifico") Optional<String> termoEspecifico,
			@RequestParam("documento") Optional<String> documento,
			@RequestParam("status") Optional<String> status) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checklist> cq = cb.createQuery(Checklist.class);
		Root<Checklist> root = cq.from(Checklist.class);
		
		Predicate[] predicates = extractPredicates(cb, root, nucleo, tipoProcesso, termoGeral, termoEspecifico, documento, status);
		cq.select(cq.getSelection()).where(predicates);

		TypedQuery<Checklist> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	private Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, 
			Optional<String> nucleo, 
			Optional<String> tipoProcesso, 
			Optional<String> termoGeral, 
			Optional<String> termoEspecifico, 
			Optional<String> documento, 
			Optional<String> status) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (nucleo.isPresent() && EPAUtil.isNotBlank(nucleo.get())) {
			Join<Checklist, Nucleo> joinNucleo = root.join("nucleo", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinNucleo.get("nome")), "%" + nucleo.get().toLowerCase() + "%"));
		}
		if (tipoProcesso.isPresent() && EPAUtil.isNotBlank(tipoProcesso.get())) {
			Join<Checklist, TipoProcesso> joinTipoProcesso = root.join("tipoProcesso", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTipoProcesso.get("nome")), "%" + tipoProcesso.get().toLowerCase() + "%"));
		}
		if (termoGeral.isPresent() && EPAUtil.isNotBlank(termoGeral.get())) {
			Join<Checklist, TermoGeral> joinTermoGeral = root.join("termoGeral", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTermoGeral.get("nome")), "%" + termoGeral.get().toLowerCase() + "%"));
		}
		if (termoEspecifico.isPresent() && EPAUtil.isNotBlank(termoEspecifico.get())) {
			Join<Checklist, TermoEspecifico> joinTermoEspecifico = root.join("termoEspecifico", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTermoEspecifico.get("nome")), "%" + termoEspecifico.get().toLowerCase() + "%"));
		}
		if (documento.isPresent() && EPAUtil.isNotBlank(documento.get())) {
			Join<Checklist, Documento> joinDocumento = root.join("documento", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinDocumento.get("nome")), "%" + documento.get().toLowerCase() + "%"));
		}
		if (status.isPresent() && EPAUtil.isNotBlank(status.get())) {
			predicates.add(cb.equal(root.get("status"), BooleanUtils.toBoolean(status.get())));
		}
        return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	@GetMapping({"/buscarpaginado"})
	public Page<Checklist> buscarPaginado(
			@RequestParam("nucleo") Optional<String> nucleo, 
			@RequestParam("tipoProcesso") Optional<String> tipoProcesso,
			@RequestParam("termoGeral") Optional<String> termoGeral,
			@RequestParam("termoEspecifico") Optional<String> termoEspecifico,
			@RequestParam("documento") Optional<String> documento,
			@RequestParam("status") Optional<String> status,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		Specification<Checklist> specification = new Specification<Checklist>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Checklist> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate[] predicates = extractPredicates(criteriaBuilder, root, nucleo, tipoProcesso, termoGeral, termoEspecifico, documento, status);
				query.distinct(true);
				return criteriaBuilder.and(predicates);
			}
		};
		PageRequest pageable = PageRequest.of(page.get(), size.get(), Direction.ASC, "id");
		Page<Checklist> resultados = repository.findAll(specification, pageable);
		return resultados;
	}
	
}
