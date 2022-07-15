import { Dia } from "../models/enums/dia";

export class DiaUtils {
	public static getNumeroDia(dia: Dia): number {
		let numeroDia;
		switch (dia) {
			case Dia.LUNES:
				numeroDia = 1;
				break;
			case Dia.MARTES:
				numeroDia = 2;
				break;
			case Dia.MIERCOLES:
				numeroDia = 3;
				break;
			case Dia.JUEVES:
				numeroDia = 4;
				break;
			case Dia.VIERNES:
				numeroDia = 5;
				break;
		}

		return numeroDia;
	}

	public static getDiaByDiaString(dia: string): Dia | null {
		let diaEnum: Dia | null = null;
		switch (dia.toUpperCase()) {
			case Dia.LUNES:
				diaEnum = Dia.LUNES;
				break;
			case Dia.MARTES:
				diaEnum = Dia.MARTES;
				break;
			case Dia.MIERCOLES:
				diaEnum = Dia.MIERCOLES;
				break;
			case Dia.JUEVES:
				diaEnum = Dia.JUEVES;
				break;
			case Dia.VIERNES:
				diaEnum = Dia.VIERNES;
				break;
		}

		return diaEnum;
	}
}