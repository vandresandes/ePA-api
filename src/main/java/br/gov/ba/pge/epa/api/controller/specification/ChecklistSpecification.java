package br.gov.ba.pge.epa.api.controller.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.filter.ChecklistFilter;

public class ChecklistSpecification extends BaseSpecification<Checklist> {

	public static Specification<Checklist> filtrar(ChecklistFilter filter) {
		return (root, cq, cb) -> {
			Predicate[] predicates = extractPredicates(cb, root, filter);
			cq.distinct(true);
			return cb.and(predicates);
		};
	}

	private static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, ChecklistFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (StringUtils.isNotBlank(filter.getNomeNucleo())) {
				Join<Checklist, Nucleo> joinNucleo = root.join("nucleo", JoinType.INNER);
				predicates.add(cb.like(cb.lower(joinNucleo.get("nome")), containsLowerCase(filter.getNomeNucleo())));
			}
			if (StringUtils.isNotBlank(filter.getNomeTipoProcesso())) {
				Join<Checklist, TipoProcesso> joinTipoProcesso = root.join("tipoProcesso", JoinType.INNER);
				predicates.add(cb.like(cb.lower(joinTipoProcesso.get("nome")), containsLowerCase(filter.getNomeTipoProcesso())));
			}
			if (StringUtils.isNotBlank(filter.getNomeTermoGeral())) {
				Join<Checklist, TermoGeral> joinTermoGeral = root.join("termoGeral", JoinType.INNER);
				predicates.add(cb.like(cb.lower(joinTermoGeral.get("nome")), containsLowerCase(filter.getNomeTermoGeral())));
			}
			if (StringUtils.isNotBlank(filter.getNomeTermoEspecifico())) {
				Join<Checklist, TermoEspecifico> joinTermoEspecifico = root.join("termoEspecifico", JoinType.INNER);
				predicates.add(cb.like(cb.lower(joinTermoEspecifico.get("nome")), containsLowerCase(filter.getNomeTermoEspecifico())));
			}
			if (StringUtils.isNotBlank(filter.getNomeDocumento())) {
				Join<Checklist, Documento> joinDocumento = root.join("documento", JoinType.INNER);
				predicates.add(cb.like(cb.lower(joinDocumento.get("nome")), containsLowerCase(filter.getNomeDocumento())));
			}
			if (StringUtils.isNotBlank(filter.getObrigatorio())) {
				predicates.add(cb.equal(root.get("obrigatorio"), BooleanUtils.toBoolean(filter.getObrigatorio())));
			}
			if (StringUtils.isNotBlank(filter.getCondicao())) {
				predicates.add(cb.like(cb.lower(root.get("condicao")), containsLowerCase(filter.getCondicao())));
			}
			if (StringUtils.isNotBlank(filter.getComplexidade())) {
				predicates.add(cb.equal(root.get("complexidade"), filter.getComplexidade()));
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
