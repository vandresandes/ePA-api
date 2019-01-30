package br.gov.ba.pge.epa.api.controller.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.repository.filter.NucleoFilter;

public class NucleoSpecification extends BaseSpecification<Nucleo> {

	public static Specification<Nucleo> buscar(NucleoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

	private static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, NucleoFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (filter.getId() != null) {
				predicates.add(cb.equal(root.get("id"), filter.getId()));
			}
			if (StringUtils.isNotBlank(filter.getNome())) {
				predicates.add(cb.like(cb.lower(root.get("nome")), containsLowerCase(filter.getNome())));
			}
			if (filter.getMateria() != null) {
				Join<Nucleo, Materia> materia = root.join("materia", JoinType.INNER);
				
				if (filter.getMateria().getId() != null) {
					predicates.add(cb.equal(materia.get("id"), filter.getMateria().getId()));
				}
				if (StringUtils.isNotBlank(filter.getMateria().getNome())) {
					predicates.add(cb.like(cb.lower(materia.get("nome")), containsLowerCase(filter.getMateria().getNome())));
				}
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
