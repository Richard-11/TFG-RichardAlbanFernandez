package es.uva.inf.tutorias.business.domain.enums;

public enum TipoHorarioEnum {
	PRIMER_CUATRIMESTRE((short) 1, "Primer Cuatrimestre"),
	SEGUNDO_CUATRIMESTRE((short) 2, "Segundo Cuatrimestre"), 
	ANUAL((short) 3, "Anual");

	public short id;
	public String tipo;

	private TipoHorarioEnum(short id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}
	
	public static TipoHorarioEnum getById(short id) {
		for (TipoHorarioEnum tipoHorario : TipoHorarioEnum.values()) {
			if (tipoHorario.id == id) {
				return tipoHorario;
			}
		}
		
		return TipoHorarioEnum.ANUAL;
	}
}
