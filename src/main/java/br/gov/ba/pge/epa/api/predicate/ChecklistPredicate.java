package br.gov.ba.pge.epa.api.predicate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.gov.ba.pge.epa.api.model.Checklist;
import br.gov.ba.pge.epa.api.model.Documento;
import br.gov.ba.pge.epa.api.model.Materia;
import br.gov.ba.pge.epa.api.model.Nucleo;
import br.gov.ba.pge.epa.api.model.TermoEspecifico;
import br.gov.ba.pge.epa.api.model.TermoGeral;
import br.gov.ba.pge.epa.api.model.TipoProcesso;
import br.gov.ba.pge.epa.api.repository.filter.ChecklistFilter;

public class ChecklistPredicate extends BasePredicate {

	public static Predicate[] extractPredicates(CriteriaBuilder cb, Root<?> root, ChecklistFilter filter) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (filter != null) {
			if (filter.getId() != null) {
				predicates.add(cb.equal(root.get("id"), filter.getId()));
			}
			if (filter.getObrigatorio() != null) {
				predicates.add(cb.equal(root.get("obrigatorio"), filter.getObrigatorio()));
			}
			if (StringUtils.isNotBlank(filter.getCondicao())) {
				predicates.add(cb.like(cb.lower(root.get("condicao")), containsLowerCase(filter.getCondicao())));
			}
			if (filter.getApresentarJustificativaCondicao() != null) {
				predicates.add(cb.equal(root.get("apresentarJustificativaCondicao"), filter.getApresentarJustificativaCondicao()));
			}
			if (StringUtils.isNotBlank(filter.getComplexidade())) {
				predicates.add(cb.equal(root.get("complexidade"), filter.getComplexidade()));
			}
			
			// FK's
			
			if (filter.getNucleo() != null) {
				Join<Checklist, Nucleo> nucleo = root.join("nucleo", JoinType.INNER);
				
				if (filter.getNucleo().getId() != null) {
					predicates.add(cb.equal(nucleo.get("id"), filter.getNucleo().getId()));
				}
				if (StringUtils.isNotBlank(filter.getNucleo().getNome())) {
					predicates.add(cb.like(cb.lower(nucleo.get("nome")), containsLowerCase(filter.getNucleo().getNome())));
				}
				
				if (filter.getNucleo().getMateria() != null) {
					Join<Nucleo, Materia> materia = nucleo.join("materia", JoinType.INNER);
					
					if (filter.getNucleo().getMateria().getId() != null) {
						predicates.add(cb.equal(materia.get("id"), filter.getNucleo().getMateria().getId()));
					}
					if (StringUtils.isNotBlank(filter.getNucleo().getMateria().getNome())) {
						predicates.add(cb.like(cb.lower(materia.get("nome")), containsLowerCase(filter.getNucleo().getMateria().getNome())));
					}
				}
			}
			
			if (filter.getTipoProcesso() != null) {
				Join<Checklist, TipoProcesso> tipoProcesso = root.join("tipoProcesso", JoinType.INNER);
				
				if (filter.getTipoProcesso().getId() != null) {
					predicates.add(cb.equal(tipoProcesso.get("id"), filter.getTipoProcesso().getId()));
				}
				if (StringUtils.isNotBlank(filter.getTipoProcesso().getNome())) {
					predicates.add(cb.like(cb.lower(tipoProcesso.get("nome")), containsLowerCase(filter.getTipoProcesso().getNome())));
				}
			}
			
			if (filter.getTermoGeral() != null) {
				Join<Checklist, TermoGeral> termoGeral = root.join("termoGeral", JoinType.INNER);
				
				if (filter.getTermoGeral().getId() != null) {
					predicates.add(cb.equal(termoGeral.get("id"), filter.getTermoGeral().getId()));
				}
				if (StringUtils.isNotBlank(filter.getTermoGeral().getNome())) {
					predicates.add(cb.like(cb.lower(termoGeral.get("nome")), containsLowerCase(filter.getTermoGeral().getNome())));
				}
			}
			
			if (filter.getTermoEspecifico() != null) {
				Join<Checklist, TermoEspecifico> termoEspecifico = root.join("termoEspecifico", JoinType.INNER);
				
				if (filter.getTermoEspecifico().getId() != null) {
					predicates.add(cb.equal(termoEspecifico.get("id"), filter.getTermoEspecifico().getId()));
				}
				if (StringUtils.isNotBlank(filter.getTermoEspecifico().getNome())) {
					predicates.add(cb.like(cb.lower(termoEspecifico.get("nome")), containsLowerCase(filter.getTermoEspecifico().getNome())));
				}
			}
			
			if (filter.getDocumento() != null) {
				Join<Checklist, Documento> documento = root.join("documento", JoinType.INNER);
				
				if (filter.getDocumento().getId() != null) {
					predicates.add(cb.equal(documento.get("id"), filter.getDocumento().getId()));
				}
				if (StringUtils.isNotBlank(filter.getDocumento().getNome())) {
					predicates.add(cb.like(cb.lower(documento.get("nome")), containsLowerCase(filter.getDocumento().getNome())));
				}
			}
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
