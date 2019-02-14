package br.gov.ba.pge.epa.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.ba.pge.epa.api.config.TokenAuthenticationService;
import br.gov.ba.pge.epa.api.model.security.UsuarioRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Value("${url.autenticacao.office.365}")
	private String url;
	@Value("${servico.autenticacao.office.365}")
	private String servico;

	@PostMapping
	public ResponseEntity<String> autenticar(@Valid @RequestBody UsuarioRequest usuarioRequest, HttpServletResponse responseLogin) {
		String responseString = null;
		int statusCode = 0;
		SSLContextBuilder sslcontext = new SSLContextBuilder();
		try {
			sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslcontext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
			HttpPost httpPost = new HttpPost(getURI());
			httpPost.setHeader("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			httpPost.setEntity(getEntity(usuarioRequest));
			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			statusCode = response.getStatusLine().getStatusCode();
			
			if (isAutenticado(statusCode)) {
				TokenAuthenticationService.addAuthentication(responseLogin, usuarioRequest.getUsuario());
			}
			
			response.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isAutenticado(statusCode) ? ResponseEntity.ok(responseString) : new ResponseEntity<String>(responseString, HttpStatus.valueOf(statusCode));
	}

	private HttpEntity getEntity(UsuarioRequest usuarioRequest) {
		try {
			return new UrlEncodedFormEntity(getParametros(usuarioRequest));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<NameValuePair> getParametros(UsuarioRequest usuarioRequest) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("usuario", usuarioRequest.getUsuario()));
		params.add(new BasicNameValuePair("senha", usuarioRequest.getSenha()));
		return params;
	}

	private String getURI() {
		return url.concat("/").concat(servico);
	}

	private boolean isAutenticado(int statusCode) {
		return statusCode == HttpStatus.OK.value();
	}

}
