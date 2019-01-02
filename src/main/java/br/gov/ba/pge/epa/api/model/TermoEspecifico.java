package br.gov.ba.pge.epa.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.gov.ba.pge.epa.api.model.infra.BaseEntity;

@Entity
@Table(name = "tb_tes_termo_especifico")
public class TermoEspecifico extends BaseEntity<Long> {

	@Id
	@Column(name = "tes_id_termo_especifico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "tes_no_nome")
	private String nome;
	
	public TermoEspecifico() {}
	
	public TermoEspecifico(String nome) {
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

}
