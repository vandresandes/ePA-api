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
			@RequestParam("nucleo") String nucleo, 
			@RequestParam("tipoProcesso") String tipoProcesso,
			@RequestParam("termoGeral") String termoGeral,
			@RequestParam("termoEspecifico") String termoEspecifico,
			@RequestParam("documento") String documento,
			@RequestParam("status") String status) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checklist> cq = cb.createQuery(Checklist.class);
		Root<Checklist> root = cq.from(Checklist.class);
		
		Predicate[] predicates = extractPredicates(cb, root, nucleo, tipoProcesso, termoGeral, termoEspecifico, documento, status);
		cq.select(cq.getSelection()).where(predicates);

		TypedQuery<Checklist> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	private Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, 
			String nucleo, 
			String tipoProcesso, 
			String termoGeral, 
			String termoEspecifico, 
			String documento, 
			String status) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (EPAUtil.isNotBlank(nucleo)) {
			Join<Checklist, Nucleo> joinNucleo = root.join("nucleo", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinNucleo.get("nome")), "%" + nucleo.toLowerCase() + "%"));
		}
		if (EPAUtil.isNotBlank(tipoProcesso)) {
			Join<Checklist, TipoProcesso> joinTipoProcesso = root.join("tipoProcesso", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTipoProcesso.get("nome")), "%" + tipoProcesso.toLowerCase() + "%"));
		}
		if (EPAUtil.isNotBlank(termoGeral)) {
			Join<Checklist, TermoGeral> joinTermoGeral = root.join("termoGeral", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTermoGeral.get("nome")), "%" + termoGeral.toLowerCase() + "%"));
		}
		if (EPAUtil.isNotBlank(termoEspecifico)) {
			Join<Checklist, TermoEspecifico> joinTermoEspecifico = root.join("termoEspecifico", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinTermoEspecifico.get("nome")), "%" + termoEspecifico.toLowerCase() + "%"));
		}
		if (EPAUtil.isNotBlank(documento)) {
			Join<Checklist, Documento> joinDocumento = root.join("documento", JoinType.LEFT);
			predicates.add(cb.like(cb.lower(joinDocumento.get("nome")), "%" + documento.toLowerCase() + "%"));
		}
		if (EPAUtil.isNotBlank(status)) {
			predicates.add(cb.equal(root.get("status"), BooleanUtils.toBoolean(status)));
		}
		return predicates.toArray(new Predicate[] {});
	}

}
