package es.uva.inf.tutorias.telegram.business.domain.models;

/**
 * The persistent class for the alumno database table.
 * 
 */
public class Alumno extends Usuario {
	private static final long serialVersionUID = 1L;

	private Integer nia;

	public Alumno() {
	}

	public Integer getNia() {
		return this.nia;
	}

	public void setNia(Integer nia) {
		this.nia = nia;
	}

}