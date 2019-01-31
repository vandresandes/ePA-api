package br.gov.ba.pge.epa.api.model.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.gov.ba.pge.epa.api.model.enums.dto.EnumDto;

public enum EnumPrioridadeTramitacao {
	
	SEM_PRIORIDADE("SEM_PRIORIDADE", "Sem prioridade"), 
	PESSOA_COM_IDADE_SUPERIOR_A_60_ANOS("PESSOA_COM_IDADE_SUPERIOR_A_60_ANOS", "Pessoa com idade superior a 60 anos"),
	PESSOA_COM_NECESSIDADES_ESPECIAIS_OU_DOENCA_GRAVE("PESSOA_COM_NECESSIDADES_ESPECIAIS_OU_DOENCA_GRAVE", "Pessoa com necessidades especiais ou doença grave");

	String valor;
	String descricao;

	private EnumPrioridadeTramitacao(String valor, String descricao) {
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
		for (EnumPrioridadeTramitacao item : values()) {
			retorno.add(new EnumDto(item.getValor(), item.getDescricao()));
		}
		return retorno;
	}

	public static EnumDto buscarPorValor(String valor) {
		if (StringUtils.isBlank(valor)) {
			return null;
		}
		
		for (EnumPrioridadeTramitacao item : values()) {
			if (item.getValor().equalsIgnoreCase(valor)) {
				return new EnumDto(item.getValor(), item.getDescricao());
			}
		}
		
		return null;
	}

}

