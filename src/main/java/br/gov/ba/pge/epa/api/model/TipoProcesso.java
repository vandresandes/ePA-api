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
@Table(name = "tb_tpp_tipo_processo")
public class TipoProcesso extends BaseEntity<Long> {

	@Id
	@Column(name = "tpp_id_tipo_processo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "tpp_no_nome")
	private String nome;
	
	public TipoProcesso() {}
	
	public TipoProcesso(String nome) {
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
