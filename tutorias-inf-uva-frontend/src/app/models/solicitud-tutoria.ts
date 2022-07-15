import { Alumno } from "./alumno";
import { Asignatura } from "./asignatura";
import { EstadoSolicitudEnum } from "./enums/estado-solicitud-enum";
import { EstadoSolicitud } from "./estado-solicitud";
import { Profesor } from "./profesor";

export class SolicitudTutoria {
	id?: number;
	asunto?: string;
	comentarioAlumno?: string;
	fechaSolicitud?: Date;
	fechaTutoria?: Date | string;
	horaInicio?: Date | string;
	horaFin?: Date | string;
	grupal?: boolean;
	motivoRechazo?: string;
	rechazadaVistaPorAlumno?: boolean;
	ubicacionTutoria?: string;
	comentarioProfesor?: string;
	propuestaNuevoHorario?: boolean;
	estadoSolicitud?: EstadoSolicitud | EstadoSolicitudEnum;
	asignatura?: Asignatura;
	alumno?: Alumno
	profesor?: Profesor

	constructor(values: any) {
		this.asunto = values.asunto;
		this.comentarioAlumno = values.comentarioAlumno;
		this.fechaSolicitud = values.fechaSolicitud;
		this.fechaTutoria = values.fechaTutoria;
		this.horaInicio = values.horaInicio;
		this.horaFin = values.horaFin;
		this.grupal = values.grupal;
		this.alumno = values.alumno;
		this.profesor = values.profesor;
		this.asignatura = values.asignatura;
	}
}