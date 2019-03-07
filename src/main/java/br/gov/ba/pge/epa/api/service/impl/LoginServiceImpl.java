package br.gov.ba.pge.epa.api.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import br.gov.ba.pge.epa.api.model.security.LoginResponse;
import br.gov.ba.pge.epa.api.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Value("${url.autenticacao.office.365}")
	private String url;
	@Value("${servico.autenticacao.office.365}")
	private String servico;

	@Override
	public LoginResponse autenticar(String username, String password) {
		String responseString = null;
		int statusCode = 0;
		SSLContextBuilder sslcontext = new SSLContextBuilder();
		try {
			sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslcontext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
			HttpPost httpPost = new HttpPost(getURI());
			httpPost.setHeader("content-type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			httpPost.setEntity(getEntity(username, password));
			CloseableHttpResponse response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			statusCode = response.getStatusLine().getStatusCode();
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

		return new LoginResponse(responseString, statusCode);
	}

	private HttpEntity getEntity(String username, String password) {
		try {
			return new UrlEncodedFormEntity(getParametros(username, password));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<NameValuePair> getParametros(String username, String password) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("usuario", username));
		params.add(new BasicNameValuePair("senha", password));
		return params;
	}

	private String getURI() {
		return url.concat("/").concat(servico);
	}

}
