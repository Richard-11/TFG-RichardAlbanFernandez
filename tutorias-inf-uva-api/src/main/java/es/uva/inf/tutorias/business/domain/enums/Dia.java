package es.uva.inf.tutorias.business.domain.enums;

public enum Dia {
	LUNES((short) 1, "lunes"), 
	MARTES((short) 2, "martes"), 
	MIERCOLES((short) 3, "miercoles"),
	JUEVES((short) 4, "jueves"), 
	VIERNES((short) 5, "viernes");

	public Short id;
	public String nombre;

	private Dia(short id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public static Dia getById(short id) {

		for (Dia dia : Dia.values()) {
			if (dia.id == id) {
				return dia;
			}
		}

		return Dia.LUNES;
	}
}
