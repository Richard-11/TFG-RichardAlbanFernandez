package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.enums.EstadoSolicitud;
import es.uva.inf.tutorias.persistence.entities.EstadoSolicitudDB;

public class EstadoSolicitudConverter {

	public static EstadoSolicitud convertToEstadoSolicitud(EstadoSolicitudDB estadoSolicitudDB) {
		return EstadoSolicitud.getById(estadoSolicitudDB.getId());
	}

	public static EstadoSolicitudDB convertToEstadoSolicitudDB(EstadoSolicitud estadoSolicitud) {
		EstadoSolicitudDB estadoSolicitudDB = new EstadoSolicitudDB();
		estadoSolicitudDB.setId(estadoSolicitud.id);
		estadoSolicitudDB.setEstado(estadoSolicitud.estado);
		
		return estadoSolicitudDB;
	}
}
