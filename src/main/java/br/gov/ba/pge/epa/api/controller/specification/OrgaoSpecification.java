package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Orgao;
import br.gov.ba.pge.epa.api.predicate.OrgaoPredicate;
import br.gov.ba.pge.epa.api.repository.filter.OrgaoFilter;

public class OrgaoSpecification {

	public static Specification<Orgao> buscar(OrgaoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = OrgaoPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
