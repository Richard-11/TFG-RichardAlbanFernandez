package es.uva.inf.tutorias.telegram.business.domain.models;

public class StartCommand implements BotCommand {
	
	private TutoriasInfUVaBot bot;
	
	public StartCommand(TutoriasInfUVaBot bot) {
		this.bot = bot;
	}

	@Override
	public void execute(Chat chat) {
		bot.processStart(chat);
	}

}
