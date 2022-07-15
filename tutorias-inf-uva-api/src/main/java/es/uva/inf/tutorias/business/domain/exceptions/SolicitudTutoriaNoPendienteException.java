package es.uva.inf.tutorias.business.domain.exceptions;

import es.uva.inf.tutorias.business.domain.enums.EstadoSolicitud;

public class SolicitudTutoriaNoPendienteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SolicitudTutoriaNoPendienteException(EstadoSolicitud estadoActual) {
		super("No se puede cancelar pues la solicitud ya fue " + estadoActual.estado.toLowerCase());
	}

}
