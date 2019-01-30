package br.gov.ba.pge.epa.api.repository.filter;

public class NucleoFilter {

	private Long id;
	private String nome;

	private TipoProcessoFilter tipoProcesso;
	private TermoGeralFilter termoGeral;
	private TermoEspecificoFilter termoEspecifico;
	private DocumentoFilter documento;
	private MateriaFilter materia;

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

	public MateriaFilter getMateria() {
		return materia;
	}

	public void setMateria(MateriaFilter materia) {
		this.materia = materia;
	}

}
