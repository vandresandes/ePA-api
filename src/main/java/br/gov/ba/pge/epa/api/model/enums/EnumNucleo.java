package br.gov.ba.pge.epa.api.model.enums;

public enum EnumNucleo {

	NCAD(1L, "NCAD"), 
	NLC(2L, "NLC"), 
	NPA(3L, "NPA"), 
	NPE(4L, "NPE"), 
	NPMA(5L, "NPMA"), 
	NPREV(6L, "NPREV"),
	NSAS(7L, "NSAS"), 
	NSESAB(8L, "NSESAB"), 
	NSSP(9L, "NSSP"), 
	NTCE(10L, "NTCE");

	private Long valor;
	private String nome;

	private EnumNucleo(Long valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public Long getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

}
