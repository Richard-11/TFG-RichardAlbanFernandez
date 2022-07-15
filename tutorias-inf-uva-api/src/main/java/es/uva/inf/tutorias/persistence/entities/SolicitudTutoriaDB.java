package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the solicitudtutoria database table.
 * 
 */
@Entity
@Table(name = "solicitudtutoria")
public class SolicitudTutoriaDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOLICITUDTUTORIA_ID_GENERATOR", sequenceName="SECUENCIA_ID_SOLICITUDTUTORIA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SOLICITUDTUTORIA_ID_GENERATOR")
	private Long id;

	private String asunto;

	@Column(name = "comentarioalumno")
	private String comentarioAlumno;

	@Column(name = "fechasolicitud")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSolicitud;

	@Column(name = "fechatutoria")
	@Temporal(TemporalType.DATE)
	private Date fechaTutoria;

	@Column(name = "horainicio")
	private LocalTime horaInicio;

	@Column(name = "horafin")
	private LocalTime horaFin;

	private Boolean grupal;
	
	@Column(name = "motivorechazo")
	private String motivoRechazo;

	@Column(name = "rechazadavistaporalumno")
	private Boolean rechazadaVistaPorAlumno;

	@Column(name = "ubicaciontutoria")
	private String ubicacionTutoria;

	@Column(name = "comentarioprofesor")
	private String comentarioProfesor;

	@Column(name = "propuestanuevohorario")
	private boolean propuestaNuevoHorario;

	@ManyToOne
	@JoinColumn(name="estadoid")
	private EstadoSolicitudDB estadoSolicitud;

	@ManyToOne
	@JoinColumn(name="codigoasignatura")
	private AsignaturaDB asignatura;

	@ManyToOne
	@JoinColumn(name="idalumno")
	private AlumnoDB alumno;

	@ManyToOne
	@JoinColumn(name="idprofesor")
	private ProfesorDB profesor;

	public SolicitudTutoriaDB() {
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

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaTutoria() {
		return this.fechaTutoria;
	}

	public void setFechaTutoria(Date fechaPlanificada) {
		this.fechaTutoria = fechaPlanificada;
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
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

	public EstadoSolicitudDB getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadoSolicitudDB estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public AsignaturaDB getAsignatura() {
		return this.asignatura;
	}

	public void setAsignatura(AsignaturaDB asignatura) {
		this.asignatura = asignatura;
	}

	public AlumnoDB getAlumno() {
		return this.alumno;
	}

	public void setAlumno(AlumnoDB alumno) {
		this.alumno = alumno;
	}

	public ProfesorDB getProfesor() {
		return this.profesor;
	}

	public void setProfesor(ProfesorDB profesor) {
		this.profesor = profesor;
	}
	
}