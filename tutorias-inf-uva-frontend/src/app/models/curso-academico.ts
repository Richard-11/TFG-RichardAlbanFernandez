import { MencionTitulacion } from "./mencion-titulacion";
import { Titulacion } from "./titulacion";

export class CursoAcademico {
	id?: number;
	curso?: string;
	cursoNumerico?: number;
	titulacion?: Titulacion;
	menciones?: MencionTitulacion[];

	constructor(id?: number, curso?: string, cursoNumerico?: number, titulacion?: Titulacion, menciones?: MencionTitulacion[]) {
		if (id) {
			this.id = id;
		}

		if (curso) {
			this.curso = curso;
		}

		if (cursoNumerico) {
			this.cursoNumerico = cursoNumerico;
		}

		if (titulacion) {
			this.titulacion = titulacion;
		}

		if (menciones) {
			this.menciones = menciones;
		}
	}
}