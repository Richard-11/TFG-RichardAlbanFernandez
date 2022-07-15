package es.uva.inf.tutorias.telegram.business.domain.models;

public class WebCommand implements BotCommand {

	private TutoriasInfUVaBot bot;

	public WebCommand(TutoriasInfUVaBot bot) {
		this.bot = bot;
	}

	@Override
	public void execute(Chat chat) {
		bot.processWeb(chat);
	}

}
