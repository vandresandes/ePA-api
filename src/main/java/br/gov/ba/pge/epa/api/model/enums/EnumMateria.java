package br.gov.ba.pge.epa.api.model.enums;

public enum EnumMateria {
	
	CONTROLE_EXTERNO(1L, "Controle Externo"),
	DISCIPLINAR_E_CONTRATOS(2L, "Disciplinar e Sancionatório"),
	LICITACOES_E_CONTRATOS(3L, "Licitações e contratos"),
	PARCERIAS(4L, "Parcerias (Convênios, Contratos de Gestão)"),
	PATRIMONIO_PUBLICO_E_MEIO_AMBIENTE(5L, "Patrimônio Público e Meio Ambiente"),
	PESSOAL(6L, "Pessoal"),
	PREVIDENCIA(7L, "Previdência");
	
	private Long valor;
	private String descricao;

	private EnumMateria(Long valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Long getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EnumMateria buscarPeloValor(Long valor) {
        for (EnumMateria item : values()) {
            if (item.getValor().longValue() == valor.longValue()) {
                return item;
            }
        }
        return null;
    }
		
}
