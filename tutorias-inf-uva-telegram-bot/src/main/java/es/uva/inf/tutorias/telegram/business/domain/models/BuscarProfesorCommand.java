package es.uva.inf.tutorias.telegram.business.domain.models;

public class BuscarProfesorCommand implements BotCommand {
	
	private TutoriasInfUVaBot bot;

	public BuscarProfesorCommand(TutoriasInfUVaBot bot) {
		this.bot = bot;
	}

	@Override
	public void execute(Chat chat) {
		bot.processBuscarProfesor(chat);
	}

}
