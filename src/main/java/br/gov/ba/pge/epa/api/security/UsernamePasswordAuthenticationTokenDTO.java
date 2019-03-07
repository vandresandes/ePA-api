package br.gov.ba.pge.epa.api.security;

import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsernamePasswordAuthenticationTokenDTO extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String orgao;

	/**
	 * Construtor.
	 * 
	 * @param principal
	 * @param credentials
	 * @param nome
	 * @param orgao
	 */
	public UsernamePasswordAuthenticationTokenDTO(Object principal, Object credentials, String nome, String orgao) {
		super(principal, credentials, new ArrayList<>());
		this.nome = nome;
		this.orgao = orgao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

}
