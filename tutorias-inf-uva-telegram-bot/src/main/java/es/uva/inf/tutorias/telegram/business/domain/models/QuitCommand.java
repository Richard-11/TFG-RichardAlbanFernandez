package es.uva.inf.tutorias.telegram.business.domain.models;

public class QuitCommand implements BotCommand {

	private TutoriasInfUVaBot bot;

	public QuitCommand(TutoriasInfUVaBot bot) {
		this.bot = bot;
	}

	@Override
	public void execute(Chat chat) {
		bot.processQuit(chat);
	}

}
