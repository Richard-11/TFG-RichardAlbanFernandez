import { EstadoSolicitudEnum } from "./enums/estado-solicitud-enum";

export class EstadoSolicitud {
	id: number;
	estado: string;

	constructor(id: number, estado: string) {
		this.id = id;
		this.estado = estado;
	}

	public static getEstadoSolicitudByEnum(estadoEnum: EstadoSolicitudEnum): EstadoSolicitud {
		switch (estadoEnum) {
			case EstadoSolicitudEnum.ACEPTADA:
				return new EstadoSolicitud(2, 'Aceptada');
			case EstadoSolicitudEnum.RECHAZADA:
				return new EstadoSolicitud(3, 'Rechazada');
			case EstadoSolicitudEnum.CONFIRMADA:
				return new EstadoSolicitud(4, 'Confirmada');
			case EstadoSolicitudEnum.CANCELADA:
				return new EstadoSolicitud(5, 'Cancelada');
			default:
				return new EstadoSolicitud(1, 'Pendiente');
		}
	}
}