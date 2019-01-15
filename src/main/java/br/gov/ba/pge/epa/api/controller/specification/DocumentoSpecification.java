package br.gov.ba.pge.epa.api.controller.specification;

import org.springframework.data.jpa.domain.Specification;

import br.gov.ba.pge.epa.api.model.Documento;

public class DocumentoSpecification extends BaseSpecification<Documento> {

	public static Specification<Documento> name(String nome) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get("nome")), containsLowerCase(nome));
	}
}
