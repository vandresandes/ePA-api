package br.gov.ba.pge.epa.api.predicate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.gov.ba.pge.epa.api.repository.filter.TermoEspecificoFilter;

public class TermoEspecificoPredicate extends BasePredicate {

	public static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, TermoEspecificoFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (filter.getId() != null) {
				predicates.add(cb.equal(root.get("id"), filter.getId()));
			}
			if (StringUtils.isNotBlank(filter.getNome())) {
				predicates.add(cb.like(cb.lower(root.get("nome")), containsLowerCase(filter.getNome())));
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
