package es.uva.inf.tutorias.rest.exceptions;

public class UsuarioBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioBadRequestException() {
		super();
	}
	
	public UsuarioBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
