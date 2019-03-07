package br.gov.ba.pge.epa.api.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.gov.ba.pge.epa.api.model.security.LoginResponse;
import br.gov.ba.pge.epa.api.service.LoginService;
import br.gov.ba.pge.epa.api.util.EPAUtil;

@Configuration
public class AuthenticationProviderConfig implements AuthenticationProvider {

	@Autowired
	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		validarParametros(authentication);
		String name = authentication.getName();
        String password = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;
		
		LoginResponse loginResponse = loginService.autenticar(name, password);
		String responseEntity = loginResponse.getResponseEntity();
		int statusCode = loginResponse.getStatusCode();
		
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		switch (httpStatus) {
		case OK:
			String nome = EPAUtil.getReadTree(responseEntity, "nome");
			String orgao = EPAUtil.getReadTree(responseEntity, "orgao");
			
			UsernamePasswordAuthenticationTokenDTO user = new UsernamePasswordAuthenticationTokenDTO(name, password, nome, orgao);
			return user;
		default:
			String error = EPAUtil.getReadTree(responseEntity, "error");
			throw new BadCredentialsException(error);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public void validarParametros(Authentication authentication) {
		if (StringUtils.isBlank(authentication.getName())) {
			throw new BadCredentialsException("Usuário obrigatório");
		}
		if (authentication.getCredentials() == null || StringUtils.isBlank(authentication.getCredentials().toString())) {
			throw new BadCredentialsException("Senha obrigatório");
		}
	}

}
