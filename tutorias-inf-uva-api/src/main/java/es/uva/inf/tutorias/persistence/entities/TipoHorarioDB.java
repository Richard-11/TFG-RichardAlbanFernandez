package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tipohorario database table.
 * 
 */
@Entity
@Table(name = "tipohorario")
public class TipoHorarioDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String tipo;
	
	@Column(name = "fechainicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	@Column(name = "fechafin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	public TipoHorarioDB() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}