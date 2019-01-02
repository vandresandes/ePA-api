package br.gov.ba.pge.epa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.gov.ba.pge.epa.api.model.enums.EnumTipoDocumento;
import br.gov.ba.pge.epa.api.model.infra.BaseEntity;

@Entity
@Table(name = "tb_doc_documento")
public class Documento extends BaseEntity<Long> {

	@Id
	@Column(name = "doc_id_documento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "doc_no_nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "doc_tp_tipo")
	private EnumTipoDocumento tipo;
	
	public Documento() {}
	
	public Documento(String nome) {
		this.nome = nome;
	}

	@Override
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

}
