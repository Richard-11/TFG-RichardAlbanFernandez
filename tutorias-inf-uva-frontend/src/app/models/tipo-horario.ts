export class TipoHorario {
	id?: number;
	tipo?: string;
	fechaInicio?: Date;
	fechaFin?: Date;

	constructor(id?: number, tipo?: string, fechaInicio?: Date, fechaFin?: Date) {
		if (id) {
			this.id = id;
		}

		if (tipo) {
			this.tipo = tipo;
		}

		if (fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		if (fechaFin) {
			this.fechaFin = fechaFin;
		}
	}
}