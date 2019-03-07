package br.gov.ba.pge.epa.api.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (authentication.getUserAuthentication() instanceof UsernamePasswordAuthenticationTokenDTO) {
			UsernamePasswordAuthenticationTokenDTO user = (UsernamePasswordAuthenticationTokenDTO) authentication.getUserAuthentication();
			
			Map<String, Object> info = new HashMap<>();
			info.put("nome", user.getNome());
			info.put("orgao", user.getOrgao());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		}
		
        return accessToken;
	}

}
