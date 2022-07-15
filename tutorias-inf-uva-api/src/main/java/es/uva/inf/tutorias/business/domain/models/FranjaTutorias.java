package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.time.LocalTime;

import es.uva.inf.tutorias.business.domain.enums.Dia;

public class FranjaTutorias implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Dia dia;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private String centro;
	private String despacho;

	public FranjaTutorias() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dia getDia() {
		return this.dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

//	public HorarioTutoriasDB getHorarioTutorias() {
//		return this.horarioTutorias;
//	}
//
//	public void setHorarioTutorias(HorarioTutoriasDB horarioTutorias) {
//		this.horarioTutorias = horarioTutorias;
//	}

}