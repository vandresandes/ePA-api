package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.predicate.NucleoPredicate;
import br.gov.ba.pge.epa.api.repository.filter.NucleoFilter;

public class NucleoSpecification {

	public static Specification<Nucleo> buscar(NucleoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = NucleoPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
