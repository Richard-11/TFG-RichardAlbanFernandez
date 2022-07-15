package es.uva.inf.tutorias.rest.exceptions;

public class AsignaturaBadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AsignaturaBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
