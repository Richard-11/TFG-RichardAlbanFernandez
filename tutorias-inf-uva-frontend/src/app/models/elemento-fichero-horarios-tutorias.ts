import { TipoHorarioEnum } from "./enums/tipo-horario-enum";

export interface ElementoFicheroHorariosTutorias {
	dia: string,
	horaInicio: string,
	horaFin: string,
	centro: string,
	despacho: string,
	tipoHorario: TipoHorarioEnum
}