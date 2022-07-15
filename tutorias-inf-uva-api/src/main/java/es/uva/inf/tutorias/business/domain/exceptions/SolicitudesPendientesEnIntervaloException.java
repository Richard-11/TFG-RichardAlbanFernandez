package es.uva.inf.tutorias.business.domain.exceptions;

public class SolicitudesPendientesEnIntervaloException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SolicitudesPendientesEnIntervaloException() {
		super("Tienes solicitudes pendientes que se solapan con la nueva solicitud");
	}

}
