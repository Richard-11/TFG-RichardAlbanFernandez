package es.uva.inf.tutorias.telegram.business.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientRequestException;

public class ApiWebClientException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private HttpStatus statusCode;
	private String response;

	public ApiWebClientException(HttpStatus statusCode, String responseBodyAsString) {
		this.statusCode = statusCode;
		this.response = responseBodyAsString;
	}

	public ApiWebClientException(WebClientRequestException e) {
		super(e);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getResponse() {
		return response;
	}

}
