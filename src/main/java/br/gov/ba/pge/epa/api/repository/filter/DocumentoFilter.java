package br.gov.ba.pge.epa.api.repository.filter;

import br.gov.ba.pge.epa.api.model.enums.EnumTipoDocumento;

public class DocumentoFilter {

	private Long id;
	private String nome;
	private EnumTipoDocumento tipo;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumTipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoDocumento tipo) {
		this.tipo = tipo;
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
