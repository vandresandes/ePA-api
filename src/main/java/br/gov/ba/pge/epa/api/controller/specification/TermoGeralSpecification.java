package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.predicate.TermoGeralPredicate;
import br.gov.ba.pge.epa.api.repository.filter.TermoGeralFilter;

public class TermoGeralSpecification extends BaseSpecification<TermoGeral> {

	public static Specification<TermoGeral> buscar(TermoGeralFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = TermoGeralPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
