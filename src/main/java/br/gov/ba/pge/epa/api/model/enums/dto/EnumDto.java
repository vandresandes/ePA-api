package br.gov.ba.pge.epa.api.model.enums.dto;

public class EnumDto {

	private Object valor;
	private String descricao;

	public EnumDto(Object valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Object getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

}
