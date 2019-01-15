package br.gov.ba.pge.epa.api.controller.specification;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TipoProcesso;

public class TipoProcessoSpecification extends BaseSpecification<TipoProcesso> {

	public static Specification<TipoProcesso> name(String nome) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get("nome")), containsLowerCase(nome));
	}
}
