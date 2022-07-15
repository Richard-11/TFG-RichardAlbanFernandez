import { CursoAcademico } from "./curso-academico";
import { NivelTitulacion } from "./nivel-titulacion";

export class Titulacion {
	codigo?: number;
	nombre?: string;
	plan?: number;
	nivelTitulacion?: NivelTitulacion;
	cursosAcademicos?: CursoAcademico[];

	constructor(codigo?: number, nombre?: string, plan?: number, nivelTitulacion?: NivelTitulacion, cursosAcademicos?: CursoAcademico[]) {
		if (codigo) {
			this.codigo = codigo;
		}

		if (nombre) {
			this.nombre = nombre;
		}

		if (plan) {
			this.plan = plan;
		}

		if (nivelTitulacion) {
			this.nivelTitulacion = nivelTitulacion;
		}

		if (cursosAcademicos) {
			this.cursosAcademicos = cursosAcademicos;
		}
	}
}