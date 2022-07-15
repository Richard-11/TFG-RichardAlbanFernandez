package es.uva.inf.tutorias.business.domain.exceptions;

public class SolicitudTutoriaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SolicitudTutoriaNotFoundException() {
		super("No se encontró ninguna solicitud de tutoría con ese id");
	}

}
