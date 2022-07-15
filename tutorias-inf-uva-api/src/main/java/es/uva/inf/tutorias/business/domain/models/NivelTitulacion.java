package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;


/**
 * The persistent class for the niveltitulacion database table.
 * 
 */
public class NivelTitulacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private short id;
	private String nivel;

	public NivelTitulacion() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

}