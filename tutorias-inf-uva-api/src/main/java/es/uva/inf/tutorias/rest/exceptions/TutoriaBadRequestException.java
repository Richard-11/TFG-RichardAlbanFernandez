package es.uva.inf.tutorias.rest.exceptions;

public class TutoriaBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TutoriaBadRequestException() {
		super("Petición inadecuada");
	}

	public TutoriaBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

}
