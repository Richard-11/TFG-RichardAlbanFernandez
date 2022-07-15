import { Asignatura } from "./asignatura";
import { EstadoSolicitud } from "./estado-solicitud";
import { Profesor } from "./profesor";

export interface FiltrosTutoria {
	estadoSolicitud?: EstadoSolicitud;
	cancelada?: boolean | null;
	grupal?: boolean | null;
	profesor?: Profesor;
	asignatura?: Asignatura;
	fechaTutoria?: string ;
	horaInicio?: string;
	horaFin?: string;
}