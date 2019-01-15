package br.gov.ba.pge.epa.api.controller.specification;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.TermoGeral;

public class TermoGeralSpecification extends BaseSpecification<TermoGeral> {

	public static Specification<TermoGeral> name(String nome) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get("nome")), containsLowerCase(nome));
	}
}
