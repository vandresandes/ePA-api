package br.gov.ba.pge.epa.api.repository.filter;

public class ChecklistFilter {

	private Long idNucleo;
	private Long idTipoProcesso;
	private Long idTermoGeral;
	private Long idTermoEspecifico;
	private Long idDocumento;
	private Long idMateria;

	private String nomeNucleo;
	private String nomeTipoProcesso;
	private String nomeTermoGeral;
	private String nomeTermoEspecifico;
	private String nomeDocumento;
	private String nomeMateria;

	private String status;

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

	public String getNomeNucleo() {
		return nomeNucleo;
	}

	public void setNomeNucleo(String nomeNucleo) {
		this.nomeNucleo = nomeNucleo;
	}

	public String getNomeTipoProcesso() {
		return nomeTipoProcesso;
	}

	public void setNomeTipoProcesso(String nomeTipoProcesso) {
		this.nomeTipoProcesso = nomeTipoProcesso;
	}

	public String getNomeTermoGeral() {
		return nomeTermoGeral;
	}

	public void setNomeTermoGeral(String nomeTermoGeral) {
		this.nomeTermoGeral = nomeTermoGeral;
	}

	public String getNomeTermoEspecifico() {
		return nomeTermoEspecifico;
	}

	public void setNomeTermoEspecifico(String nomeTermoEspecifico) {
		this.nomeTermoEspecifico = nomeTermoEspecifico;
	}

	public String getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(String nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

	public String getNomeMateria() {
		return nomeMateria;
	}

	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
