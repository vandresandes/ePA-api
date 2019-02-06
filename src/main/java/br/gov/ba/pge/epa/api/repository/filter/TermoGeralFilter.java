package br.gov.ba.pge.epa.api.repository.filter;

public class TermoGeralFilter {

	private Long id;
	private String nome;
	private Long idNucleo;
	private Long idTipoProcesso;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private Long idMateria;

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

	public Long getIdNucleo() {
		return idNucleo;
	}

	public void setIdNucleo(Long idNucleo) {
		this.idNucleo = idNucleo;
	}

	public Long getIdTipoProcesso() {
		return idTipoProcesso;
	}

	public void setIdTipoProcesso(Long idTipoProcesso) {
		this.idTipoProcesso = idTipoProcesso;
	}

	public Long getIdTermoEspecifico() {
		return idTermoEspecifico;
	}

	public void setIdTermoEspecifico(Long idTermoEspecifico) {
		this.idTermoEspecifico = idTermoEspecifico;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
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
