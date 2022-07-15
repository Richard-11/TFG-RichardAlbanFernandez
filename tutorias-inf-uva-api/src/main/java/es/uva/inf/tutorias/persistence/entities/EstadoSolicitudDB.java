package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the estadosolicitud database table.
 * 
 */
@Entity
@Table(name = "estadosolicitud")
public class EstadoSolicitudDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String estado;

	public EstadoSolicitudDB() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}