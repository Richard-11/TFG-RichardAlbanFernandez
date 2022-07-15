package es.uva.inf.tutorias.business.domain.exceptions;

public class TutoriaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TutoriaNotFoundException() {
		super("No se encontró ninguna tutoría con ese id");
	}
}
