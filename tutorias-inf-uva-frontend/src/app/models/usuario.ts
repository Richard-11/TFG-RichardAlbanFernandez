import { Asignatura } from "./asignatura";
import { Titulacion } from "./titulacion";

export class Usuario {
	identificador: string;
	nombre: string;
	apellidos: string;
	nif: string;
	email: string;
	titulaciones: Titulacion[];
	asignaturas: Asignatura[];

	constructor(profesorJSON: any) {
		this.identificador = profesorJSON.identificador;
		this.nombre = profesorJSON.nombre;
		this.apellidos = profesorJSON.apellidos;
		this.nif = profesorJSON.nif;
		this.email = profesorJSON.email;
		this.titulaciones = profesorJSON.titulaciones;
		this.asignaturas = profesorJSON.asignaturas;
	}
	
}