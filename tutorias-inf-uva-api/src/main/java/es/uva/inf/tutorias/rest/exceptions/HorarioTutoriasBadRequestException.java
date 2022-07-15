package es.uva.inf.tutorias.rest.exceptions;

public class HorarioTutoriasBadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public HorarioTutoriasBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
