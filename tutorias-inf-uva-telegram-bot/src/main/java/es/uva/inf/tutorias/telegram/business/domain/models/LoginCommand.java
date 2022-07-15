package es.uva.inf.tutorias.telegram.business.domain.models;

public class LoginCommand implements BotCommand {

	private TutoriasInfUVaBot bot;

	public LoginCommand(TutoriasInfUVaBot bot) {
		this.bot = bot;
	}

	@Override
	public void execute(Chat chat) {
		bot.processLogin(chat);
	}

}
