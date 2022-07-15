package es.uva.inf.tutorias.business.domain.exceptions;

public class HorariosTutoriasNoValidosException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public HorariosTutoriasNoValidosException() {
		super("Los nuevos horarios de tutorías no son válidos");
	}

}
