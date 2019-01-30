package br.gov.ba.pge.epa.api.model.enums;

public enum EnumTipoDocumento {

	DOCUMENTO("DOCUMENTO", "Documento"), 
	INFORMACAO("INFORMACAO", "Informação");

	String valor;
	String descricao;

	private EnumTipoDocumento(String valor, String descricao) {
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
