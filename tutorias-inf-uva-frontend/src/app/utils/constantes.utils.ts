import { EstadoSolicitud } from "../models/estado-solicitud";

export const MOMENT_DATE_FORMAT = 'YYYY-MM-DD';
export const MOMENT_TIME_FORMAT = 'HH:mm';
export const MOMENT_DATETIME_FORMAT = 'YYYY-MM-DD HH:mm'
export const DURACIONES_ESTIMADAS = [{
	label: '15 minutos',
	minutos: 15
}, {
	label: '30 minutos',
	minutos: 30
}, {
	label: '45 minutos',
	minutos: 45
}, {
	label: '1 hora',
	minutos: 60
}];

export const ESTADOS_SOLICITUD: EstadoSolicitud[] = [
	{ id: 1, estado: 'Pendiente' },
	{ id: 2, estado: 'Aceptada' },
	{ id: 3, estado: 'Rechazada' },
	{ id: 4, estado: 'Confirmada' },
	{ id: 5, estado: 'Cancelada' }
];