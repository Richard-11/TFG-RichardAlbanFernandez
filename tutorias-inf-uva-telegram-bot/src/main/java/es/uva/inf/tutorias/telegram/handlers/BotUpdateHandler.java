package es.uva.inf.tutorias.telegram.handlers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import es.uva.inf.tutorias.telegram.business.domain.models.BotTask;
import es.uva.inf.tutorias.telegram.business.domain.models.TutoriasInfUVaBot;
import es.uva.inf.tutorias.telegram.business.services.IAlumnoService;
import es.uva.inf.tutorias.telegram.business.services.IAsignaturaService;
import es.uva.inf.tutorias.telegram.business.services.IChatService;
import es.uva.inf.tutorias.telegram.business.services.IProfesorService;
import es.uva.inf.tutorias.telegram.business.services.ITitulacionService;
import es.uva.inf.tutorias.telegram.configuration.BotConfiguration;
import es.uva.inf.tutorias.telegram.configuration.TutoriasInfUVaWebProperties;

@Component
public class BotUpdateHandler extends TelegramLongPollingBot {

	private TutoriasInfUVaBot bot;

	@Autowired
	private IAlumnoService alumnoService;

	@Autowired
	private IProfesorService profesorService;

	@Autowired
	private ITitulacionService titulacionService;

	@Autowired
	private IAsignaturaService asignaturaService;

	@Autowired
	private IChatService chatService;

	private ExecutorService executorService;

	public BotUpdateHandler(final BotConfiguration botConfiguration, final TutoriasInfUVaWebProperties webProperties) {
		bot = new TutoriasInfUVaBot(getOptions(), botConfiguration, webProperties);
	}

	@PostConstruct
	private void init() {
		bot.setAlumnoService(alumnoService);
		bot.setProfesorService(profesorService);
		bot.setTitulacionService(titulacionService);
		bot.setAsignaturaService(asignaturaService);
		bot.setChatService(chatService);

		Runtime runtime = Runtime.getRuntime();
		executorService = Executors.newFixedThreadPool(runtime.availableProcessors());
	}

	@Override
	public void onUpdateReceived(Update update) {
		executorService.execute(new BotTask(bot, update, chatService));
	}

	@Override
	public String getBotUsername() {
		return bot.getUsername();
	}

	@Override
	public String getBotToken() {
		return bot.getBotToken();
	}

}
