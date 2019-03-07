package br.gov.ba.pge.epa.api.model.security;

public class LoginResponse {

	private String responseEntity;
	private int statusCode;

	/**
	 * Construtor.
	 * 
	 * @param responseEntity
	 * @param statusCode
	 */
	public LoginResponse(String responseEntity, int statusCode) {
		this.responseEntity = responseEntity;
		this.statusCode = statusCode;
	}

	public String getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(String responseEntity) {
		this.responseEntity = responseEntity;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
