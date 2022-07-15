package es.uva.inf.tutorias.business.domain.exceptions;

public class ExistenTutoriasEnIntervaloParaProfesorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExistenTutoriasEnIntervaloParaProfesorException() {
		super("Existen tutorías en ese intervalo para el profesor");
	}

}
