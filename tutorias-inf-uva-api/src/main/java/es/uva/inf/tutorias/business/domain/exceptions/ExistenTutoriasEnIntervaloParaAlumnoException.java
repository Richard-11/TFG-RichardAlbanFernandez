package es.uva.inf.tutorias.business.domain.exceptions;

public class ExistenTutoriasEnIntervaloParaAlumnoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ExistenTutoriasEnIntervaloParaAlumnoException() {
		super("Ya tienes tutorías en ese intervalo");
	}

}
