package es.uva.inf.tutorias.telegram.business.domain.enums;

public enum TipoBusquedaProfesor {
	GENERAL(1, "Búsqueda general"),
	MIS_ASIGNATURAS(2, "A partir de mis asignaturas"),
	POR_NOMBRE_ASIGNATURA(3, "Por nombre asignatura");
	
	public Integer id;
	public String tipoBusqueda;
	
	private TipoBusquedaProfesor(Integer id, String tipoBusqueda) {
		this.id = id;
		this.tipoBusqueda = tipoBusqueda;
	}
}
