export class NivelTitulacion {
	id?: number;
	nivel?: string;

	constructor(id?: number, nivel?: string) {
		if (id) {
			this.id = id;
		}

		if (nivel) {
			this.nivel = nivel;
		}
	}
}