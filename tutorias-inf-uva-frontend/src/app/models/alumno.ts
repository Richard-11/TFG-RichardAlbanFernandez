import { Usuario } from "./usuario";

export class Alumno extends Usuario {
	nia: number;

	constructor(alumnoJSON: any) {
		super(alumnoJSON);
		this.nia = alumnoJSON.nia;
	}

}