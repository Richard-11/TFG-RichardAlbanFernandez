import { Dia } from "./enums/dia";

export class FranjaTutorias {
	id?: number;
	dia?: Dia;
	horaInicio?: Date | string;
	horaFin?: Date | string;
	centro?: string;
	despacho?: string;

	constructor(id?: number, dia?: Dia, horaInicio?: Date | string, horaFin?: Date | string, centro?: string, despacho?: string) {
		if (id) {
			this.id = id;
		}

		if (dia) {
			this.dia = dia;
		}

		if (horaInicio) {
			this.horaInicio = horaInicio;
		}

		if (horaFin) {
			this.horaFin = horaFin;
		}

		if (centro) {
			this.centro = centro;
		}

		if (despacho) {
			this.despacho = despacho;
		}
	}
}