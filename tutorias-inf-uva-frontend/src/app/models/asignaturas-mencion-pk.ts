export class AsignaturasMencionPK {
	mencionId?: number;
	codigo?: number;

	constructor(mencionId?: number, codigo?: number) {
		if (mencionId) {
			this.mencionId = mencionId;
		}

		if (codigo) {
			this.codigo = codigo;
		}
	}
}