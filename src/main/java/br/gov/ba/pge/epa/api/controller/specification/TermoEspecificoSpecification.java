package br.gov.ba.pge.epa.api.controller.specification;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TermoEspecifico;

public class TermoEspecificoSpecification extends BaseSpecification<TermoEspecifico> {

	public static Specification<TermoEspecifico> name(String nome) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get("nome")), containsLowerCase(nome));
	}
}
