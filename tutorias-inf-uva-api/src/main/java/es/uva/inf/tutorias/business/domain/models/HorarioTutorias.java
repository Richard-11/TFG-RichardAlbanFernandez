package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.util.List;

public class HorarioTutorias implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private List<FranjaTutorias> franjasTutorias;
	private TipoHorario tipoHorario;
	private Profesor profesor;

	public HorarioTutorias() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FranjaTutorias> getFranjasTutorias() {
		return this.franjasTutorias;
	}

	public void setFranjasTutorias(List<FranjaTutorias> franjasTutorias) {
		this.franjasTutorias = franjasTutorias;
	}

	public TipoHorario getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

}