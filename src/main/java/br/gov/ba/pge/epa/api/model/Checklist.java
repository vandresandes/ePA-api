package br.gov.ba.pge.epa.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.gov.ba.pge.epa.api.model.enums.EnumPrioridade;

@Entity
@Table(name = "tb_checklist")
public class Checklist implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_id_nucleo")
	@NotNull
	private Nucleo nucleo;

	@ManyToOne
	@JoinColumn(name = "fk_id_tipo_processo")
	@NotNull
	private TipoProcesso tipoProcesso;

	@ManyToOne
	@JoinColumn(name = "fk_id_termo_geral")
	@NotNull
	private TermoGeral termoGeral;

	@ManyToOne
	@JoinColumn(name = "fk_id_termo_especifico")
	@NotNull
	private TermoEspecifico termoEspecifico;

	@ManyToOne
	@JoinColumn(name = "fk_id_documento")
	@NotNull
	private Documento documento;

	@NotNull
	@Column(name = "bo_obrigatorio")
	private Boolean obrigatorio;

	@Column(name = "condicao")
	@Max(100)
	private String condicao;

	@Column(name = "bo_apresentar_justificativa_condicao")
	@NotNull
	private Boolean apresentarJustificativaCondicao;

	@Enumerated(EnumType.STRING)
	@Column(name = "complexidade")
	private EnumPrioridade complexidade;

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

	public EnumPrioridade getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(EnumPrioridade complexidade) {
		this.complexidade = complexidade;
	}
	
}
