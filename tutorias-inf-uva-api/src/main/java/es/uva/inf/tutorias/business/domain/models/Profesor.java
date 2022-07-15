package es.uva.inf.tutorias.business.domain.models;

import java.util.List;

/**
 * The persistent class for the profesor database table.
 * 
 */
public class Profesor extends Usuario {
	private static final long serialVersionUID = 1L;
	
	private String centroHabitual;
	private String despacho;
	private String telefono;
	private List<HorarioTutorias> horariosTutorias;

	public Profesor() {
	}

	public String getCentroHabitual() {
		return centroHabitual;
	}

	public void setCentroHabitual(String centroHabitual) {
		this.centroHabitual = centroHabitual;
	}

	public String getDespacho() {
		return this.despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<HorarioTutorias> getHorariosTutorias() {
		return this.horariosTutorias;
	}

	public void setHorariosTutorias(List<HorarioTutorias> horariosTutorias) {
		this.horariosTutorias = horariosTutorias;
	}

}