import { AsignaturasMencion } from "./asignaturas-mencion";
import { CursoAcademico } from "./curso-academico";
import { Usuario } from "./usuario";

export class Asignatura {
	codigo?: number;
	nombre?: string;
	acronimo?: string;
	cursos?: CursoAcademico[];
	asignaturasMencion?: AsignaturasMencion[];
	usuarios?: Usuario[];

	constructor(codigo?: number, nombre?: string, acronimo?: string, cursos?: CursoAcademico[], asignaturasMencion?: AsignaturasMencion[], usuarios?: Usuario[]) {
		if (codigo) {
			this.codigo = codigo;
		}

		if (nombre) {
			this.nombre = nombre;
		}

		if (acronimo) {
			this.acronimo = acronimo;
		}

		if (cursos) {
			this.cursos = cursos;
		}

		if (asignaturasMencion) {
			this.asignaturasMencion = asignaturasMencion;
		}

		if (usuarios) {
			this.usuarios = usuarios;
		}
	}
}