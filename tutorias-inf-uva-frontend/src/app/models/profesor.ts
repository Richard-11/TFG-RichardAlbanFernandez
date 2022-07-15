import { HorarioTutorias } from "./horario-tutorias";
import { Usuario } from "./usuario";

export class Profesor extends Usuario {
	centroHabitual: string;
	despacho: string;
	telefono: string;
	horariosTutorias: HorarioTutorias[];

	constructor(profesorJSON: any) {
		super(profesorJSON);
		this.identificador = profesorJSON.identificador;
		this.nombre = profesorJSON.nombre;
		this.apellidos = profesorJSON.apellidos;
		this.nif = profesorJSON.nif;
		this.email = profesorJSON.email;
		this.titulaciones = profesorJSON.titulaciones;
		this.asignaturas = profesorJSON.asignaturas;
		this.centroHabitual = profesorJSON.centroHabitual;
		this.despacho = profesorJSON.despacho;
		this.telefono = profesorJSON.telefono;
		this.horariosTutorias = profesorJSON.horariosTutorias;
	}
	
}