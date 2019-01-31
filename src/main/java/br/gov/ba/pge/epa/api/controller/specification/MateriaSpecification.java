package br.gov.ba.pge.epa.api.controller.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.predicate.MateriaPredicate;
import br.gov.ba.pge.epa.api.repository.filter.MateriaFilter;

public class MateriaSpecification {
	public static Specification<Materia> buscar(MateriaFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = MateriaPredicate.extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

}
