package es.uva.inf.tutorias.telegram.business.domain.models;

import java.util.Objects;

import org.telegram.telegrambots.meta.api.objects.Update;

import es.uva.inf.tutorias.telegram.business.domain.enums.BotCommandEnum;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoBuscarProfesor;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoLogin;
import es.uva.inf.tutorias.telegram.business.domain.enums.TipoBusquedaProfesor;

public class Chat {
	private Long id;
	private Long chatId;
	private String telegramUser;
	private String identificadorEscuela;
	private String nombreCompletoAlumno;
	private Boolean sesionIniciada;
	private Integer codigoTitulacion;
	private Integer cursoId;
	private Integer mencionId;
	private Integer codigoAsignatura;
	private String profesorId;
	private String nombreAsignatura;
	private BotCommandEnum currentCommand;
	private PasoLogin pasoLogin;
	private TipoBusquedaProfesor tipoBusquedaProfesor;
	private PasoBuscarProfesor pasoBuscarProfesor;
	private Update update;

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

	@Override
	public int hashCode() {
		return Objects.hash(chatId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return Objects.equals(chatId, other.chatId);
	}

	public Update getUpdate() {
		return update;
	}

	public void setUpdate(Update update) {
		this.update = update;
	}

}
