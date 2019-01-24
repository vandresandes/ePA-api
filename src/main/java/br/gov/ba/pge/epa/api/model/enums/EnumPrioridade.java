package br.gov.ba.pge.epa.api.model.enums;

public enum EnumPrioridade {

	BAIXA("BAIXA", "Baixa"), 
	MEDIA("MEDIA", "Média"),
	ALTA("ALTA", "Alta");

	String valor;
	String descricao;

	private EnumPrioridade(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

}
