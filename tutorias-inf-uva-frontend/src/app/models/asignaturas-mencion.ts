import { CursoAcademico } from "./curso-academico";
import { MencionTitulacion } from "./mencion-titulacion";

export class AsignaturasMencion {
	id?: AsignaturasMencion;
	mencion?: MencionTitulacion;
	curso?: CursoAcademico;

	constructor(id?: AsignaturasMencion, mencion?: MencionTitulacion, curso?: CursoAcademico) {
		if (id) {
			this.id = id;
		}

		if (mencion) {
			this.mencion = mencion;
		}

		if (curso) {
			this.curso = curso;
		}
	}
}