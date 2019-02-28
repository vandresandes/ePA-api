package br.gov.ba.pge.epa.api.model.enums;

public enum EnumParametrosSistema {

	SIGLA_SISTEMA("SEI"),
	IDENTIFICACAO_SERVICO("api.seibahia"),
	ID_UNIDADE("110000006");
	
	String valor;
	
	private EnumParametrosSistema(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	
}
