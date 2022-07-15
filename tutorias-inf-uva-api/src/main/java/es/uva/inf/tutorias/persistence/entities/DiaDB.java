package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the dia database table.
 * 
 */
@Entity
@Table(name = "dia")
public class DiaDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String nombre;

	public DiaDB() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}