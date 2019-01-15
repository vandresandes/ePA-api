package br.gov.ba.pge.epa.api.controller.specification;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Nucleo;

public class NucleoSpecification extends BaseSpecification<Nucleo> {

	public static Specification<Nucleo> name(String nome) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get("nome")), containsLowerCase(nome));
	}

}
