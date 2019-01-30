package br.gov.ba.pge.epa.api.repository.filter;

public class TermoGeralFilter {

	private Long id;
	private String nome;
	private Long idNucleo;
	private Long idTipoProcesso;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private Long idMateria;

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

}
