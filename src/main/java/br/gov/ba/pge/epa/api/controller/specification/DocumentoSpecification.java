package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.predicate.DocumentoPredicate;
import br.gov.ba.pge.epa.api.repository.filter.DocumentoFilter;

public class DocumentoSpecification extends BaseSpecification<Documento> {

	public static Specification<Documento> buscar(DocumentoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = DocumentoPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}

