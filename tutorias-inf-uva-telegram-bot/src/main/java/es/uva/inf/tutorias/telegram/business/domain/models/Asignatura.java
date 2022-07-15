package es.uva.inf.tutorias.telegram.business.domain.models;

import java.io.Serializable;

public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nombre;
	private String acronimo;

	public Asignatura() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAcronimo() {
		return this.acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	
}