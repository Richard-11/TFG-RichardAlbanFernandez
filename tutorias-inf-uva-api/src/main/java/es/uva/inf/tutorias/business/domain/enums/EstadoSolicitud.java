package es.uva.inf.tutorias.business.domain.enums;

public enum EstadoSolicitud {
	PENDIENTE((short) 1, "pendiente"), 
	ACEPTADA((short) 2, "aceptada"),
	RECHAZADA((short) 3, "rechazada"),
	CONFIRMADA((short) 4, "confirmada"),
	CANCELADA((short) 5, "cancelada");
	
	public final short id;
	public final String estado;
	
	EstadoSolicitud(short id, String estado) {
		this.id = id;
		this.estado = estado;
	}
	
	public static EstadoSolicitud getById(short id) {
		for (EstadoSolicitud es : EstadoSolicitud.values()) {
			if (es.id == id) {
				return es;
			}
		}
		
		return EstadoSolicitud.PENDIENTE;
	}
}
