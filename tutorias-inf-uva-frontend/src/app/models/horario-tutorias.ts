import { FranjaTutorias } from "./franja-tutorias";
import { TipoHorario } from "./tipo-horario";

export class HorarioTutorias {
	id?: number;
	tipoHorario?: TipoHorario;
	franjasTutorias?: FranjaTutorias[];

	constructor(id?: number, tipoHorario?: TipoHorario, franjasTutorias?: FranjaTutorias[]) {
		if (id) {
			this.id = id;
		}

		if (tipoHorario) {
			this.tipoHorario = tipoHorario;
		}

		if (franjasTutorias) {
			this.franjasTutorias = franjasTutorias;
		}
	}
}