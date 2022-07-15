package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the niveltitulacion database table.
 * 
 */
@Entity
@Table(name = "niveltitulacion")
public class NivelTitulacionDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String nivel;

	public NivelTitulacionDB() {
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