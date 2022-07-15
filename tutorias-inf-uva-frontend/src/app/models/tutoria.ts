import { SolicitudTutoria } from "./solicitud-tutoria";

export class Tutoria {

	id?: number;
	cancelada?: boolean;
	motivoCancelacion?: string;
	solicitudTutoria?: SolicitudTutoria;

	constructor(id?: number, cancelada?: boolean, motivoCancelacion?: string, solicitudTutoria?: SolicitudTutoria) {
		if (id) {
			this.id = id;
		}

		if (cancelada) {
			this.cancelada = cancelada;
		}

		if (motivoCancelacion) {
			this.motivoCancelacion = motivoCancelacion;
		}

		if (solicitudTutoria) {
			this.solicitudTutoria = solicitudTutoria;
		}
	}
}