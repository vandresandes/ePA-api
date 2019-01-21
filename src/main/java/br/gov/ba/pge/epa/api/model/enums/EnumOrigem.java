package br.gov.ba.pge.epa.api.model.enums;

public enum EnumOrigem {
	
	CBMBA(9L, "CBMBA"),
	PCBA(30L, "PCBA"),
	PMBA(32L, "PMBA"),
	SEC(39L, "SEC"),
	SEPROMI(49L, "SEPROMI"),
	SESAB(51L, "SESAB"),
	SJDHDS(55L, "SJDHDS"),
	SSP(57L, "SSP"),
	OUTRAS_ORIGENS(0L, "OUTRAS_ORIGENS");
	
	private Long valor;
	private String descricao;

	private EnumOrigem(Long valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Long getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EnumOrigem buscarPeloValor(Long valor) {
        for (EnumOrigem item : values()) {
            if (item.getValor().longValue() == valor.longValue()) {
                return item;
            }
        }
        return OUTRAS_ORIGENS;
    }
	
}
