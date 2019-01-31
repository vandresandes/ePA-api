package br.gov.ba.pge.epa.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.gov.ba.pge.epa.api.model.enums.dto.EnumDto;

public enum EnumSigiloSegredoJustica {

	SEM_SIGILO("SEM_SIGILO", "Sem sigilo"), 
	SIGILOSO("SIGILOSO", "Sigiloso"),
	SEGREDO_DE_JUSTICA("SEGREDO_DE_JUSTICA", "Segredo de Justiça");

	String valor;
	String descricao;

	private EnumSigiloSegredoJustica(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<EnumDto> getValores() {
		List<EnumDto> retorno = new ArrayList<>();
		for (EnumSigiloSegredoJustica item : values()) {
			retorno.add(new EnumDto(item.getValor(), item.getDescricao()));
		}
		return retorno;
	}

	public static EnumDto buscarPorValor(String valor) {
		if (StringUtils.isBlank(valor)) {
			return null;
		}
		
		for (EnumSigiloSegredoJustica item : values()) {
			if (item.getValor().equalsIgnoreCase(valor)) {
				return new EnumDto(item.getValor(), item.getDescricao());
			}
		}
		
		return null;
	}

}
