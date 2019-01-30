package br.gov.ba.pge.epa.api.repository.filter;

public class ChecklistFilter {

	private Long id;
	private String obrigatorio;
	private String condicao;
	private String complexidade;

	// FK's
	private NucleoFilter nucleo;
	private TipoProcessoFilter tipoProcesso;
	private TermoGeralFilter termoGeral;
	private TermoEspecificoFilter termoEspecifico;
	private DocumentoFilter documento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(String obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	public NucleoFilter getNucleo() {
		return nucleo;
	}

	public void setNucleo(NucleoFilter nucleo) {
		this.nucleo = nucleo;
	}

	public TipoProcessoFilter getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(TipoProcessoFilter tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public TermoGeralFilter getTermoGeral() {
		return termoGeral;
	}

	public void setTermoGeral(TermoGeralFilter termoGeral) {
		this.termoGeral = termoGeral;
	}

	public TermoEspecificoFilter getTermoEspecifico() {
		return termoEspecifico;
	}

	public void setTermoEspecifico(TermoEspecificoFilter termoEspecifico) {
		this.termoEspecifico = termoEspecifico;
	}

	public DocumentoFilter getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoFilter documento) {
		this.documento = documento;
	}

}
