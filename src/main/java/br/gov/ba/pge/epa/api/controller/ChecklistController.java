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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.event.RecursoCriadoEvent;
import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.ChecklistRepository;

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

	@GetMapping({ "/buscar", "/buscar/{nome}" })
	public List<Checklist> buscar(@PathVariable Optional<String> nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Checklist> cq = cb.createQuery(Checklist.class);
		Root<Checklist> root = cq.from(Checklist.class);
		
		Predicate[] predicates = extractPredicates(new Checklist(), cb, root);
		cq.select(cq.getSelection()).where(predicates);

		TypedQuery<Checklist> query = entityManager.createQuery(cq);
//	    query.setFirstResult(0);
//	    query.setMaxResults(0);
		return query.getResultList();
	}

	private Predicate[] extractPredicates(Checklist filtro, CriteriaBuilder cb, Root<?> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (filtro != null) {
			if (filtro.getStatus() != null) {
				predicates.add(cb.equal(root.get("status"), filtro.getStatus()));
			}
			if (filtro.getDocumento() != null) {
				if (filtro.getDocumento().getId() != null) {
					predicates.add(cb.equal(root.get("documento"), filtro.getDocumento()));
				} else if (StringUtils.isNotBlank(filtro.getDocumento().getNome())) {
					Join<Checklist, Documento> documento = root.join("destinatarioOrgao", JoinType.LEFT);
					predicates.add(cb.like(cb.lower(documento.get("nome")), "%" + filtro.getDocumento().getNome().toLowerCase() + "%"));
				}
			}
			if (filtro.getNucleo() != null) {
				if (filtro.getNucleo().getId() != null) {
					predicates.add(cb.equal(root.get("nucleo"), filtro.getNucleo()));
				} else if (StringUtils.isNotBlank(filtro.getNucleo().getNome())) {
					Join<Checklist, Nucleo> nucleo = root.join("destinatarioOrgao", JoinType.LEFT);
					predicates.add(cb.like(cb.lower(nucleo.get("nome")), "%" + filtro.getNucleo().getNome().toLowerCase() + "%"));
				}
			}
			if (filtro.getTermoEspecifico() != null) {
				if (filtro.getTermoEspecifico().getId() != null) {
					predicates.add(cb.equal(root.get("termoEspecifico"), filtro.getTermoEspecifico()));
				} else if (StringUtils.isNotBlank(filtro.getTermoEspecifico().getNome())) {
					Join<Checklist, TermoEspecifico> termoEspecifico = root.join("destinatarioOrgao", JoinType.LEFT);
					predicates.add(cb.like(cb.lower(termoEspecifico.get("nome")), "%" + filtro.getTermoEspecifico().getNome().toLowerCase() + "%"));
				}
			}
			if (filtro.getTermoGeral() != null) {
				if (filtro.getTermoGeral().getId() != null) {
					predicates.add(cb.equal(root.get("termoGeral"), filtro.getTermoGeral()));
				} else if (StringUtils.isNotBlank(filtro.getTermoGeral().getNome())) {
					Join<Checklist, TermoGeral> termoGeral = root.join("destinatarioOrgao", JoinType.LEFT);
					predicates.add(cb.like(cb.lower(termoGeral.get("nome")), "%" + filtro.getTermoGeral().getNome().toLowerCase() + "%"));
				}
			}
			if (filtro.getTipoProcesso() != null) {
				if (filtro.getTipoProcesso().getId() != null) {
					predicates.add(cb.equal(root.get("tipoProcesso"), filtro.getTipoProcesso()));
				} else if (StringUtils.isNotBlank(filtro.getTipoProcesso().getNome())) {
					Join<Checklist, TipoProcesso> tipoProcesso = root.join("destinatarioOrgao", JoinType.LEFT);
					predicates.add(cb.like(cb.lower(tipoProcesso.get("nome")), "%" + filtro.getTipoProcesso().getNome().toLowerCase() + "%"));
				}
			}
		}
		return predicates.toArray(new Predicate[] {});
	}

}
