package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.predicate.TipoProcessoPredicate;
import br.gov.ba.pge.epa.api.repository.filter.TipoProcessoFilter;

public class TipoProcessoSpecification {

	public static Specification<TipoProcesso> buscar(TipoProcessoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = TipoProcessoPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
