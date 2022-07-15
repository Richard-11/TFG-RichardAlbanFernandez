package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tutoria database table.
 * 
 */
@Entity
@Table(name = "tutoria")
public class TutoriaDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TUTORIA_ID_GENERATOR", sequenceName="SECUENCIA_ID_TUTORIA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TUTORIA_ID_GENERATOR")
	private Long id;

	private Boolean cancelada;

	@Column(name = "motivocancelacion")
	private String motivoCancelacion;

	@OneToOne()
    @JoinColumn(name = "solicitudid", referencedColumnName = "id", insertable = false, updatable = false)
	private SolicitudTutoriaDB solicitudTutoria;
	
	@Column(name = "solicitudid")
	private Long solicitudId;

	public TutoriaDB() {
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

	public SolicitudTutoriaDB getSolicitudTutoria() {
		return solicitudTutoria;
	}

	public void setSolicitudTutoria(SolicitudTutoriaDB solicitudTutoria) {
		this.solicitudTutoria = solicitudTutoria;
	}

	public Long getSolicitudId() {
		return solicitudId;
	}

	public void setSolicitudId(Long solicitudId) {
		this.solicitudId = solicitudId;
	}

}