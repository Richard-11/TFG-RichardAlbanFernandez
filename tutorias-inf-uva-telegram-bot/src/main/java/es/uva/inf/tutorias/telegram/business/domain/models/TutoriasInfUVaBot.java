package es.uva.inf.tutorias.telegram.business.domain.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.vdurmont.emoji.EmojiParser;

import es.uva.inf.tutorias.telegram.business.domain.enums.BotCommandEnum;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoBuscarProfesor;
import es.uva.inf.tutorias.telegram.business.domain.enums.PasoLogin;
import es.uva.inf.tutorias.telegram.business.domain.enums.TipoBusquedaProfesor;
import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.exceptions.AuthenticationFailedException;
import es.uva.inf.tutorias.telegram.business.services.IAlumnoService;
import es.uva.inf.tutorias.telegram.business.services.IAsignaturaService;
import es.uva.inf.tutorias.telegram.business.services.IChatService;
import es.uva.inf.tutorias.telegram.business.services.IProfesorService;
import es.uva.inf.tutorias.telegram.business.services.ITitulacionService;
import es.uva.inf.tutorias.telegram.business.utils.BotUtils;
import es.uva.inf.tutorias.telegram.configuration.BotConfiguration;
import es.uva.inf.tutorias.telegram.configuration.TutoriasInfUVaWebProperties;

public class TutoriasInfUVaBot extends DefaultAbsSender {
	private final BotConfiguration botConfiguration;
	private final TutoriasInfUVaWebProperties webProperties;

	private IAlumnoService alumnoService;
	private IProfesorService profesorService;
	private ITitulacionService titulacionService;
	private IAsignaturaService asignaturaService;
	private IChatService chatService;

	public TutoriasInfUVaBot(DefaultBotOptions options, final BotConfiguration botConfiguration,
			final TutoriasInfUVaWebProperties webProperties) {
		super(options);

		this.botConfiguration = botConfiguration;
		this.webProperties = webProperties;
	}

	@Override
	public String getBotToken() {
		return botConfiguration.getToken();
	}

	public String getUsername() {
		return botConfiguration.getUsername();
	}

	public void setAlumnoService(IAlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	public void setProfesorService(IProfesorService profesorService) {
		this.profesorService = profesorService;
	}

	public void setTitulacionService(ITitulacionService titulacionService) {
		this.titulacionService = titulacionService;
	}

	public void setAsignaturaService(IAsignaturaService asignaturaService) {
		this.asignaturaService = asignaturaService;
	}

	public void setChatService(IChatService chatService) {
		this.chatService = chatService;
	}

	public void processStart(Chat chat) {
		BotUtils.sendMessage(this, String.format("¡Bienvenid@ a %s!", getUsername()),
				chat.getUpdate().getMessage().getChatId());
	}

	public void processLogin(Chat chat) {
		chat.setCurrentCommand(BotCommandEnum.LOGIN);

		Update update = chat.getUpdate();
		String textValue = update.getMessage().getText();

		if (chat.getSesionIniciada()) {
			BotUtils.sendMessage(this, "Sesión activa", update.getMessage().getChatId());
			return;
		}

		if (textValue.equals(BotCommandEnum.LOGIN.command)) {
			processLoginCommand(chat);
		} else {
			continueProcessingLoginCommand(chat);
		}

	}

	public void processBuscarProfesor(Chat chat) {
		chat.setCurrentCommand(BotCommandEnum.BUSCAR_PROFESOR);

		if (!chat.getSesionIniciada()) {
			BotUtils.sendLoginRequiredMessage(this, chat.getChatId());
			return;
		}

		Update update = chat.getUpdate();
		if (update != null) {
			if (update.hasMessage()) {
				processMessageFromBuscarProfesorCommand(chat);
			} else if (update.hasCallbackQuery()) {
				processCallback(chat);
			}
		}
	}

	public void processQuit(Chat chat) {
		String nombreAlumno = chat.getNombreCompletoAlumno();
		Long chatId = chat.getChatId();
		Boolean sesionIniciada = chat.getSesionIniciada();
		chatService.deleteChat(chat);

		if (sesionIniciada) {
			BotUtils.sendMessage(this, String.format("Sesión terminada. ¡Hasta la próxima, %s!", nombreAlumno), chatId);
		}
	}

	public void processWeb(Chat chat) {
		if (!chat.getSesionIniciada()) {
			BotUtils.sendLoginRequiredMessage(this, chat.getChatId());
			return;
		}

		BotUtils.sendWebButton(this, chat.getChatId(), "Abrir Tutorias InfUVa", webProperties.getUrl());
	}

	private void processMessageFromBuscarProfesorCommand(Chat chat) {
		Update update = chat.getUpdate();
		String textValue = update.getMessage().getText();

		if (textValue.equals(BotCommandEnum.BUSCAR_PROFESOR.command)) {
			processBuscarProfesorCommand(chat);
		} else {
			continueProcessingBuscarProfesorCommand(chat);
		}
	}

	private void initBuscarProfesor(Chat chat) {
		chat.setNombreAsignatura(null);
		chat.setCodigoTitulacion(null);
		chat.setCursoId(null);
		chat.setMencionId(null);
		chat.setCodigoAsignatura(null);
		chat.setProfesorId(null);
		chat.setPasoBuscarProfesor(PasoBuscarProfesor.TIPO_BUSQUEDA);
		chat.setTipoBusquedaProfesor(null);
	}

	private void processBuscarProfesorCommand(Chat chat) {
		initBuscarProfesor(chat);

		showMessageFromBuscarProfesorProcess(chat);
		chatService.updateChat(chat);
	}

	private void continueProcessingBuscarProfesorCommand(Chat chat) {
		if (chat.getPasoBuscarProfesor() == PasoBuscarProfesor.FINAL) {
			return;
		}

		boolean showMessage = true;
		switch (chat.getPasoBuscarProfesor()) {
		case NOMBRE_ASIGNATURA:
			String textValue = chat.getUpdate().getMessage().getText();
			chat.setNombreAsignatura(textValue);
			chat.setPasoBuscarProfesor(PasoBuscarProfesor.ASIGNATURAS);
			break;
		case PROFESOR:
			chat.setPasoBuscarProfesor(PasoBuscarProfesor.FINAL);
			break;
		default:
			showMessage = false;
			break;
		}

		if (showMessage) {
			showMessageFromBuscarProfesorProcess(chat);
			chatService.updateChat(chat);
		}
	}

	private void processCallback(Chat chat) {
		Update update = chat.getUpdate();
		if (update == null || !update.hasCallbackQuery() || update.getCallbackQuery().getData() == null) {
			return;
		}

		if (chat.getPasoBuscarProfesor() == PasoBuscarProfesor.FINAL) {
			return;
		}

		String response = update.getCallbackQuery().getData();
		String checkEmoji = EmojiParser.parseToUnicode(":white_check_mark:");
		String answer;
		Titulacion titulacion;
		CursoAcademico curso;

		switch (chat.getPasoBuscarProfesor()) {
		case TIPO_BUSQUEDA:
			TipoBusquedaProfesor tipoBusquedaProfesor = Arrays.asList(TipoBusquedaProfesor.values()).stream()
					.filter(t -> t.id == Integer.valueOf(response)).findFirst().get();
			answer = "Tipo de búsqueda seleccionado " + checkEmoji + ": " + tipoBusquedaProfesor.tipoBusqueda;
			BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);
			chat.setTipoBusquedaProfesor(tipoBusquedaProfesor);

			if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.GENERAL) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.TITULACIONES);
			} else if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.MIS_ASIGNATURAS) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ASIGNATURAS);
			} else if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.POR_NOMBRE_ASIGNATURA) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.NOMBRE_ASIGNATURA);
			}

			break;
		case TITULACIONES:
			try {
				chat.setCodigoTitulacion(Integer.valueOf(response));
				titulacion = titulacionService.getTitulacion(chat.getCodigoTitulacion());
				answer = "Titulacion seleccionada " + checkEmoji + ": " + titulacion.getNombre();
				BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.CURSOS);
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case CURSOS:
			try {
				chat.setCursoId(Integer.valueOf(response));
				titulacion = titulacionService.getTitulacion(chat.getCodigoTitulacion());
				curso = titulacion.getCursosAcademicos().stream().filter(c -> c.getId() == chat.getCursoId())
						.findFirst().get();
				answer = "Curso seleccionado " + checkEmoji + ": " + curso.getCurso();
				BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);

				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ASIGNATURAS);
				if (curso.getMenciones() != null && curso.getMenciones().size() > 0) {
					chat.setPasoBuscarProfesor(PasoBuscarProfesor.MENCIONES);
				}
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case MENCIONES:
			try {
				chat.setMencionId(Integer.valueOf(response));
				titulacion = titulacionService.getTitulacion(chat.getCodigoTitulacion());
				curso = titulacion.getCursosAcademicos().stream().filter(c -> c.getId() == chat.getCursoId())
						.findFirst().get();
				MencionTitulacion mencion = curso.getMenciones().stream()
						.filter(m -> m.getId() == Integer.valueOf(response)).findFirst().get();
				answer = "Mención seleccionada " + checkEmoji + ": " + mencion.getNombre();
				BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ASIGNATURAS);
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case ASIGNATURAS:
			try {
				chat.setCodigoAsignatura(Integer.valueOf(response));
				Asignatura asignatura;
				asignatura = asignaturaService.getAsignatura(chat.getCodigoAsignatura());
				answer = "Asignatura seleccionada " + checkEmoji + ": " + asignatura.getNombre();
				BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.PROFESORES);
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case PROFESORES:
			chat.setProfesorId(response);
			answer = "Profesor seleccionado " + checkEmoji;
			BotUtils.sendCallbackMessage(this, update.getCallbackQuery().getMessage(), answer);
			chat.setPasoBuscarProfesor(PasoBuscarProfesor.PROFESOR);
			break;
		default:
			break;
		}

		showMessageFromBuscarProfesorProcess(chat);
		chatService.updateChat(chat);
	}

	private void showMessageFromBuscarProfesorProcess(Chat chat) {
		String text;
		Titulacion titulacion;
		String pensiveEmoji = EmojiParser.parseToUnicode(":pensive:");
		switch (chat.getPasoBuscarProfesor()) {
		case TIPO_BUSQUEDA:
			BotUtils.sendOptionsTipoBusqueda(this, "¿De qué manera quieres empezar a buscar al profesor?",
					chat.getChatId());
			break;
		case TITULACIONES:
			try {
				List<Titulacion> titulaciones = titulacionService.getTitulaciones(chat.getIdentificadorEscuela());
				String studentsEmojis = EmojiParser.parseToUnicode(":male_student:")
						+ EmojiParser.parseToUnicode(":female_student:");
				BotUtils.sendOptions(this, "Selecciona una titulación " + studentsEmojis + ": ",
						titulaciones.toArray(new Titulacion[0]), chat.getChatId());
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}
			break;
		case CURSOS:
			try {
				titulacion = titulacionService.getTitulacion(chat.getCodigoTitulacion());
				BotUtils.sendOptions(this, "Selecciona un curso: ",
						titulacion.getCursosAcademicos().toArray(new CursoAcademico[0]), chat.getChatId());
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case MENCIONES:
			try {
				titulacion = titulacionService.getTitulacion(chat.getCodigoTitulacion());
				CursoAcademico curso = titulacion.getCursosAcademicos().stream()
						.filter(c -> c.getId() == chat.getCursoId()).findFirst().get();
				BotUtils.sendOptions(this, "Selecciona una mencion: ",
						curso.getMenciones().toArray(new MencionTitulacion[0]), chat.getChatId());
			} catch (ApiWebClientException e1) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}
			break;
		case NOMBRE_ASIGNATURA:
			text = "Introduce el nombre de la asignatura: ";
			BotUtils.sendMessage(this, text, chat.getChatId());
			break;
		case ASIGNATURAS:
			try {
				List<Asignatura> asignaturas = new ArrayList<>();
				if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.POR_NOMBRE_ASIGNATURA) {
					asignaturas = asignaturaService.getAsignaturas(chat.getIdentificadorEscuela(),
							chat.getNombreAsignatura());
				} else if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.MIS_ASIGNATURAS) {
					asignaturas = asignaturaService.getAsignaturas(chat.getIdentificadorEscuela());
				} else if (chat.getTipoBusquedaProfesor() == TipoBusquedaProfesor.GENERAL) {
					if (chat.getMencionId() == null) {
						asignaturas = asignaturaService.getAsignaturas(chat.getCodigoTitulacion(), chat.getCursoId());
					} else {
						asignaturas = asignaturaService.getAsignaturas(chat.getCodigoTitulacion(), chat.getCursoId(),
								chat.getMencionId());
					}
				}

				if (asignaturas.size() == 0) {
					BotUtils.sendMessage(this, "No se encontraron resultados " + pensiveEmoji, chat.getChatId());
					chat.setPasoBuscarProfesor(PasoBuscarProfesor.FINAL);
					break;
				}

				String booksEmoji = EmojiParser.parseToUnicode(":books:");
				BotUtils.sendOptions(this, "Selecciona una asignatura " + booksEmoji + ": ",
						asignaturas.toArray(new Asignatura[0]), chat.getChatId());
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case PROFESORES:
			try {
				List<Profesor> profesores = profesorService.getProfesores(chat.getCodigoAsignatura());
				if (profesores.isEmpty()) {
					BotUtils.sendMessage(this, "Asignatura sin docencia " + pensiveEmoji, chat.getChatId());
					chat.setPasoBuscarProfesor(PasoBuscarProfesor.FINAL);
					break;
				}

				String teachersEmojis = EmojiParser.parseToUnicode(":male_teacher:")
						+ EmojiParser.parseToUnicode(":female_teacher:");
				BotUtils.sendOptions(this, "Selecciona un profesor " + teachersEmojis + ":",
						profesores.toArray(new Profesor[0]), chat.getChatId());
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case PROFESOR:
			try {
				Profesor profesor = profesorService.getProfesor(chat.getProfesorId());
				String nombreCompleto = profesor.getNombre() + " " + profesor.getApellidos();
				String email = profesor.getEmail();
				String telefono = profesor.getTelefono();
				String centroHabitual = profesor.getCentroHabitual();
				String despacho = profesor.getDespacho();

				String profesorData = String.format(
						"Profesor: %s \nEmail: %s \nTeléfono: %s \nCentro habitual: %s \nDespacho: %s", nombreCompleto,
						email, telefono, centroHabitual, despacho);
				BotUtils.sendMessage(this, profesorData, chat.getChatId());
			} catch (ApiWebClientException e) {
				chat.setPasoBuscarProfesor(PasoBuscarProfesor.ERROR);
			}

			break;
		case FINAL:
			chat.setCurrentCommand(null);
			break;
		default:
			break;
		}

		if (chat.getPasoBuscarProfesor() == PasoBuscarProfesor.ERROR) {
			String xEmoji = EmojiParser.parseToUnicode(":x:");
			text = "Proceso terminado con error. Póngase en contacto con los técnicos";
			BotUtils.sendMessage(this, String.format("%s %s %s", xEmoji, text, xEmoji), chat.getChatId());
			chat.setPasoBuscarProfesor(PasoBuscarProfesor.FINAL);
		}
	}

	private void processLoginCommand(Chat chat) {
		chat.setPasoLogin(PasoLogin.USERNAME);

		showMessageFromLoginProcess(chat);
		chatService.updateChat(chat);
	}

	private void continueProcessingLoginCommand(Chat chat) {
		if (chat.getPasoLogin() == PasoLogin.FINAL) {
			return;
		}

		Update update = chat.getUpdate();
		Long chatId = chat.getChatId();
		String textValue = update.getMessage().getText();

		switch (chat.getPasoLogin()) {
		case USERNAME:
			chat.setIdentificadorEscuela(textValue);
			chat.setPasoLogin(PasoLogin.PASSWORD);
			break;
		case PASSWORD:
			try {
				Alumno alumno = alumnoService.login(chat.getIdentificadorEscuela(), textValue);
				chat.setSesionIniciada(true);
				chat.setNombreCompletoAlumno(alumno.getNombre() + " " + alumno.getApellidos());
				chat.setPasoLogin(PasoLogin.FINAL);
			} catch (AuthenticationFailedException e) {
				BotUtils.sendMessage(this, "Las credenciales no son válidas", chatId);
				chat.setPasoLogin(PasoLogin.USERNAME);
			} catch (ApiWebClientException e) {
				chat.setPasoLogin(PasoLogin.ERROR);
			}

			break;
		default:
			break;
		}

		showMessageFromLoginProcess(chat);

		chatService.updateChat(chat);
	}

	private void showMessageFromLoginProcess(Chat chat) {
		Long chatId = chat.getChatId();

		switch (chat.getPasoLogin()) {
		case USERNAME:
			BotUtils.sendMessage(this, "Introduce tu nombre de usuario de la Escuela:", chatId);
			break;
		case PASSWORD:
			BotUtils.sendMessage(this, "Introduce tu contraseña:", chatId);
			break;
		case FINAL:
			BotUtils.sendMessage(this,
					String.format("Sesión iniciada: ¡Bienvenid@, %s!", chat.getNombreCompletoAlumno()), chatId);
			chat.setCurrentCommand(null);
			break;
		case ERROR:
			String xEmoji = EmojiParser.parseToUnicode(":x:");
			String text = "Proceso terminado con error. Póngase en contacto con los técnicos";
			BotUtils.sendMessage(this, String.format("%s %s %s", xEmoji, text, xEmoji), chatId);
			chat.setPasoLogin(PasoLogin.FINAL);
		default:
			break;
		}
	}

}
