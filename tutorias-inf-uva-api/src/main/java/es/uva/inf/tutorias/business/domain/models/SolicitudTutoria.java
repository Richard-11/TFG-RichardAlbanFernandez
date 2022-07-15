package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import es.uva.inf.tutorias.business.domain.enums.EstadoSolicitud;

public class SolicitudTutoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String asunto;
	private String comentarioAlumno;
	private LocalDateTime fechaSolicitud;
	private LocalDate fechaTutoria;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private Boolean grupal;
	private String motivoRechazo;
	private Boolean rechazadaVistaPorAlumno;
	private String ubicacionTutoria;
	private String comentarioProfesor;
	private Boolean propuestaNuevoHorario;
	private EstadoSolicitud estadoSolicitud;
	private Asignatura asignatura;
	private Alumno alumno;
	private Profesor profesor;

	public SolicitudTutoria() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getComentarioAlumno() {
		return this.comentarioAlumno;
	}

	public void setComentarioAlumno(String comentario) {
		this.comentarioAlumno = comentario;
	}

	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaTutoria() {
		return fechaTutoria;
	}

	public void setFechaTutoria(LocalDate fechaPlanificada) {
		this.fechaTutoria = fechaPlanificada;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Boolean getGrupal() {
		return this.grupal;
	}

	public void setGrupal(Boolean grupal) {
		this.grupal = grupal;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public Boolean getRechazadaVistaPorAlumno() {
		return rechazadaVistaPorAlumno;
	}

	public void setRechazadaVistaPorAlumno(Boolean rechazadaVistaPorAlumno) {
		this.rechazadaVistaPorAlumno = rechazadaVistaPorAlumno;
	}

	public String getUbicacionTutoria() {
		return ubicacionTutoria;
	}

	public void setUbicacionTutoria(String ubicacionTutoria) {
		this.ubicacionTutoria = ubicacionTutoria;
	}

	public String getComentarioProfesor() {
		return comentarioProfesor;
	}

	public void setComentarioProfesor(String comentarioProfesor) {
		this.comentarioProfesor = comentarioProfesor;
	}

	public Boolean getPropuestaNuevoHorario() {
		return propuestaNuevoHorario;
	}

	public void setPropuestaNuevoHorario(boolean propuestaNuevoHorario) {
		this.propuestaNuevoHorario = propuestaNuevoHorario;
	}

	public EstadoSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Asignatura getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

}