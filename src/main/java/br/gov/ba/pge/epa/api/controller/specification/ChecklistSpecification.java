package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.predicate.ChecklistPredicate;
import br.gov.ba.pge.epa.api.repository.filter.ChecklistFilter;

public class ChecklistSpecification {

	public static Specification<Checklist> filtrar(ChecklistFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = ChecklistPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
