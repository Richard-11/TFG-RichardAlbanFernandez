package es.uva.inf.tutorias.business.domain.exceptions;

public class AsignaturaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AsignaturaNotFoundException() {
		super("No se encontró ninguna asignatura con ese código");
	}
	
}
