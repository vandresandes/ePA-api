package br.gov.ba.pge.epa.api.model.security;

import java.io.Serializable;

public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String login;
	private String email;
	private String orgao;

	/**
	 * Construtor.
	 */
	public UsuarioResponse() {
	}

	/**
	 * Construtor.
	 * 
	 * @param nome
	 * @param login
	 * @param email
	 * @param orgao
	 */
	public UsuarioResponse(String nome, String login, String email, String orgao) {
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.orgao = orgao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

}
