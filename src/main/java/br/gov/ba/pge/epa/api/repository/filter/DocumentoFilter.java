package br.gov.ba.pge.epa.api.repository.filter;

public class DocumentoFilter {

	private Long idNucleo;
	private Long idTipoProcesso;
	private Long idTermoGeral;
	private Long idTermoEspecifico;
	private Long idMateria;

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

	public Long getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}

}
