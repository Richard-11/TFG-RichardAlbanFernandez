package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * The persistent class for the tipohorario database table.
 * 
 */
public class TipoHorario implements Serializable {
	private static final long serialVersionUID = 1L;

	private short id;
	private String tipo;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public TipoHorario() {
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

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

}