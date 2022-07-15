export class MencionTitulacion {
	id?: number;
	nombre?: string;
	acronimo?: string;

	constructor(id?: number, nombre?: string, acronimo?: string) {
		if (id) {
			this.id = id;
		}

		if (acronimo) {
			this.acronimo = acronimo;
		}

		if (nombre) {
			this.nombre = nombre;
		}
	}
}