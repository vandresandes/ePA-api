package br.gov.ba.pge.epa.api.service;

import br.gov.ba.pge.epa.api.model.security.LoginResponse;

public interface LoginService {

	public LoginResponse autenticar(String username, String password);
}
