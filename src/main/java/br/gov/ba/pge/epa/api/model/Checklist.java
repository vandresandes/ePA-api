package br.gov.ba.pge.epa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.gov.ba.pge.epa.api.model.infra.BaseEntity;

@Entity
@Table(name = "tb_che_checklist")
public class Checklist extends BaseEntity<Long> {

	@Id
	@Column(name = "che_id_checklist")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "che_fk_nuc_id_nucleo")
	@NotNull
	private Nucleo nucleo;

	@ManyToOne
	@JoinColumn(name = "che_fk_tpp_id_tipo_processo")
	@NotNull
	private TipoProcesso tipoProcesso;

	@ManyToOne
	@JoinColumn(name = "che_fk_tge_id_termo_geral")
	@NotNull
	private TermoGeral termoGeral;

	@ManyToOne
	@JoinColumn(name = "che_fk_tes_id_termo_especifico")
	@NotNull
	private TermoEspecifico termoEspecifico;

	@ManyToOne
	@JoinColumn(name = "che_fk_doc_id_documento")
	@NotNull
	private Documento documento;

	@NotNull
	@Column(name = "che_bo_status")
	private Boolean status;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nucleo getNucleo() {
		return nucleo;
	}

	public void setNucleo(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	public TipoProcesso getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(TipoProcesso tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public TermoGeral getTermoGeral() {
		return termoGeral;
	}

	public void setTermoGeral(TermoGeral termoGeral) {
		this.termoGeral = termoGeral;
	}

	public TermoEspecifico getTermoEspecifico() {
		return termoEspecifico;
	}

	public void setTermoEspecifico(TermoEspecifico termoEspecifico) {
		this.termoEspecifico = termoEspecifico;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
