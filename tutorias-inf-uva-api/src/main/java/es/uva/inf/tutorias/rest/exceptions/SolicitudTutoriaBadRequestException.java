package es.uva.inf.tutorias.rest.exceptions;

public class SolicitudTutoriaBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SolicitudTutoriaBadRequestException() {
		super("Petición inadecuada");
	}

	public SolicitudTutoriaBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
