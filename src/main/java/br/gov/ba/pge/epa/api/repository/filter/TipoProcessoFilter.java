package br.gov.ba.pge.epa.api.repository.filter;

public class TipoProcessoFilter {

	private String nome;
	private Long idNucleo;
	private Long idTermoGeral;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private Long idMateria;

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

}
