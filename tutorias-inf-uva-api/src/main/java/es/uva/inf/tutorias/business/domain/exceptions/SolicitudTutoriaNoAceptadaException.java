package es.uva.inf.tutorias.business.domain.exceptions;

import es.uva.inf.tutorias.business.domain.enums.EstadoSolicitud;

public class SolicitudTutoriaNoAceptadaException extends Exception {

	private static final long serialVersionUID = 1L;

	public SolicitudTutoriaNoAceptadaException(EstadoSolicitud estadoActual) {
		super("No se puede confirmar pues la solicitud está en estado " + estadoActual.estado.toLowerCase());
	}

}
