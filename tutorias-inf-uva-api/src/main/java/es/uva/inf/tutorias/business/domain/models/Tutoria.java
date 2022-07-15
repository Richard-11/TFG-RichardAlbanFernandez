package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;

public class Tutoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Boolean cancelada;
	private String motivoCancelacion;
	private SolicitudTutoria solicitudTutoria;

	public Tutoria() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getCancelada() {
		return this.cancelada;
	}

	public void setCancelada(Boolean cancelada) {
		this.cancelada = cancelada;
	}

	public String getMotivoCancelacion() {
		return this.motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public SolicitudTutoria getSolicitudTutoria() {
		return solicitudTutoria;
	}

	public void setSolicitudTutoria(SolicitudTutoria solicitudTutoria) {
		this.solicitudTutoria = solicitudTutoria;
	}

}