package br.gov.ba.pge.epa.api.model.security;

public class UsuarioRequest {

	public UsuarioRequest() {
	}
	
	public UsuarioRequest(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	private String usuario;
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
