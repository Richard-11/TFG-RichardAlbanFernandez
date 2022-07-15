package es.uva.inf.tutorias.telegram.business.domain.models;

import java.io.Serializable;

public class MencionTitulacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String acronimo;
	
	public MencionTitulacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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