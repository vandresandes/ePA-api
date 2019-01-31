package br.gov.ba.pge.epa.api.controller.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Orgao;
import br.gov.ba.pge.epa.api.repository.filter.OrgaoFilter;

public class OrigemSpecification extends BaseSpecification<Orgao> {

	public static Specification<Orgao> buscar(OrgaoFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

	private static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, OrgaoFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (filter.getId() != null) {
				predicates.add(cb.equal(root.get("id"), filter.getId()));
			}
			if (StringUtils.isNotBlank(filter.getNome())) {
				predicates.add(cb.like(cb.lower(root.get("nome")), containsLowerCase(filter.getNome())));
			}
			if (StringUtils.isNotBlank(filter.getDescricao())) {
				predicates.add(cb.like(cb.lower(root.get("descricao")), containsLowerCase(filter.getDescricao())));
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
