package br.gov.ba.pge.epa.api.repository.filter;

public class TipoProcessoFilter {

	private Long id;
	private String nome;
	private Long idNucleo;
	private Long idTermoGeral;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private Long idMateria;
	private Long idOrigem;

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

	public Long getIdTermoGeral() {
		return idTermoGeral;
	}

	public void setIdTermoGeral(Long idTermoGeral) {
		this.idTermoGeral = idTermoGeral;
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

	public Long getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Long idOrigem) {
		this.idOrigem = idOrigem;
	}

}
