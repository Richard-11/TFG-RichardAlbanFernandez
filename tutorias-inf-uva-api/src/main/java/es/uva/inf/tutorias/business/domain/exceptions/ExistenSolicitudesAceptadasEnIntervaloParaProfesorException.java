package es.uva.inf.tutorias.business.domain.exceptions;

public class ExistenSolicitudesAceptadasEnIntervaloParaProfesorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExistenSolicitudesAceptadasEnIntervaloParaProfesorException() {
		super("Existen solicitudes aceptadas en ese intervalo");
	}
}
