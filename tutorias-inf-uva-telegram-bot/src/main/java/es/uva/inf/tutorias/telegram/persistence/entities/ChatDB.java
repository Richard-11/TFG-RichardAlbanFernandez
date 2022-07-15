package es.uva.inf.tutorias.telegram.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.uva.inf.tutorias.telegram.business.domain.enums.BotCommandEnum;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoBuscarProfesor;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoLogin;
import es.uva.inf.tutorias.telegram.business.domain.enums.TipoBusquedaProfesor;

@Entity
@Table(name = "chat")
public class ChatDB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "chatid", nullable = false, unique = true)
	private Long chatId;

	@Column(name = "telegramuser", unique = true)
	private String telegramUser;

	@Column(name = "identificadorescuela")
	private String identificadorEscuela;

	@Column(name = "nombrecompletoalumno")
	private String nombreCompletoAlumno;

	@Column(name = "sesionIniciada")
	private Boolean sesionIniciada;

	@Column(name = "codigoTitulacion")
	private Integer codigoTitulacion;

	@Column(name = "cursoid")
	private Integer cursoId;

	@Column(name = "mencionid")
	private Integer mencionId;

	@Column(name = "codigoasignatura")
	private Integer codigoAsignatura;

	@Column(name = "profesorid")
	private String profesorId;

	@Column(name = "nombreasignatura")
	private String nombreAsignatura;

	@Enumerated(EnumType.STRING)
	private BotCommandEnum currentCommand;

	@Enumerated(EnumType.STRING)
	private PasoLogin pasoLogin;

	@Enumerated(EnumType.STRING)
	private TipoBusquedaProfesor tipoBusquedaProfesor;

	@Enumerated(EnumType.STRING)
	private PasoBuscarProfesor pasoBuscarProfesor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public String getTelegramUser() {
		return telegramUser;
	}

	public void setTelegramUser(String telegramUser) {
		this.telegramUser = telegramUser;
	}

	public String getIdentificadorEscuela() {
		return identificadorEscuela;
	}

	public void setIdentificadorEscuela(String identificadorEscuela) {
		this.identificadorEscuela = identificadorEscuela;
	}

	public String getNombreCompletoAlumno() {
		return nombreCompletoAlumno;
	}

	public void setNombreCompletoAlumno(String nombreCompletoAlumno) {
		this.nombreCompletoAlumno = nombreCompletoAlumno;
	}

	public Boolean getSesionIniciada() {
		return sesionIniciada;
	}

	public void setSesionIniciada(Boolean sesionIniciada) {
		this.sesionIniciada = sesionIniciada;
	}

	public Integer getCodigoTitulacion() {
		return codigoTitulacion;
	}

	public void setCodigoTitulacion(Integer codigoTitulacion) {
		this.codigoTitulacion = codigoTitulacion;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public Integer getMencionId() {
		return mencionId;
	}

	public void setMencionId(Integer mencionId) {
		this.mencionId = mencionId;
	}

	public Integer getCodigoAsignatura() {
		return codigoAsignatura;
	}

	public void setCodigoAsignatura(Integer asignaturaId) {
		this.codigoAsignatura = asignaturaId;
	}

	public String getProfesorId() {
		return profesorId;
	}

	public void setProfesorId(String profesorId) {
		this.profesorId = profesorId;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public BotCommandEnum getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(BotCommandEnum currentCommand) {
		this.currentCommand = currentCommand;
	}

	public PasoLogin getPasoLogin() {
		return pasoLogin;
	}

	public void setPasoLogin(PasoLogin pasoLogin) {
		this.pasoLogin = pasoLogin;
	}

	public TipoBusquedaProfesor getTipoBusquedaProfesor() {
		return tipoBusquedaProfesor;
	}

	public void setTipoBusquedaProfesor(TipoBusquedaProfesor tipoBusquedaProfesor) {
		this.tipoBusquedaProfesor = tipoBusquedaProfesor;
	}

	public PasoBuscarProfesor getPasoBuscarProfesor() {
		return pasoBuscarProfesor;
	}

	public void setPasoBuscarProfesor(PasoBuscarProfesor pasoBuscarProfesor) {
		this.pasoBuscarProfesor = pasoBuscarProfesor;
	}

}
