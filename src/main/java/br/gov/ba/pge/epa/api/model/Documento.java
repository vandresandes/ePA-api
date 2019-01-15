package br.gov.ba.pge.epa.api.model;

import java.io.Serializable;

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

@Entity
@Table(name = "tb_documento")
public class Documento implements Serializable {

	private static final long serialVersionUID = 5546552254086883202L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private EnumTipoDocumento tipo;
	
	public Documento() {}
	
	public Documento(String nome) {
		this.nome = nome;
	}

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
