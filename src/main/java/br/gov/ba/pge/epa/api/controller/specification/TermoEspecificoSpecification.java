package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.predicate.TermoEspecificoPredicate;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;

public class TermoEspecificoSpecification {

	public static Specification<TermoEspecifico> buscar(TermoEspecificoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = TermoEspecificoPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
