package es.uva.inf.tutorias.rest.exceptions;

public class TitulacionBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TitulacionBadRequestException() {
		super("Petición inadecuada");
	}

	public TitulacionBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
