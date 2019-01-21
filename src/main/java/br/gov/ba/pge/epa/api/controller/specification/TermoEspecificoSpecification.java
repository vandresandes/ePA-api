package br.gov.ba.pge.epa.api.controller.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;

public class TermoEspecificoSpecification extends BaseSpecification<TermoEspecifico> {

	public static Specification<TermoEspecifico> buscar(TermoEspecificoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

	private static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, TermoEspecificoFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNome())) {
				predicates.add(cb.like(cb.lower(root.get("nome")), containsLowerCase(filter.getNome())));
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
