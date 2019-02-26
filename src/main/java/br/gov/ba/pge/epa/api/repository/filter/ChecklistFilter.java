package br.gov.ba.pge.epa.api.repository.filter;

public class ChecklistFilter {

	private Long id;
	private Boolean obrigatorio;
	private String condicao;
	private Boolean apresentarJustificativaCondicao;
	private String complexidade;

	// FK's
	private NucleoFilter nucleo;
	private TipoProcessoFilter tipoProcesso;
	private TermoGeralFilter termoGeral;
	private TermoEspecificoFilter termoEspecifico;
	private DocumentoFilter documento;
	private OrgaoFilter origem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getCondicao() {
		return condicao;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public Boolean getApresentarJustificativaCondicao() {
		return apresentarJustificativaCondicao;
	}

	public void setApresentarJustificativaCondicao(Boolean apresentarJustificativaCondicao) {
		this.apresentarJustificativaCondicao = apresentarJustificativaCondicao;
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

	public OrgaoFilter getOrigem() {
		return origem;
	}

	public void setOrigem(OrgaoFilter origem) {
		this.origem = origem;
	}

}
