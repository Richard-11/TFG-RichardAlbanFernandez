package es.uva.inf.tutorias.telegram.business.domain.models;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import es.uva.inf.tutorias.telegram.business.domain.enums.BotCommandEnum;
import es.uva.inf.tutorias.telegram.business.services.IChatService;

public class BotTask implements Runnable {

	private Update update;
	private TutoriasInfUVaBot bot;
	private IChatService chatService;

	public BotTask(TutoriasInfUVaBot bot, Update update, IChatService chatService) {
		this.bot = bot;
		this.update = update;
		this.chatService = chatService;
	}

	@Override
	public void run() {
		if (update.hasMessage()) {
			processMessage(update);
		} else if (update.hasCallbackQuery()) {
			processCallback(update);
		}
	}

	private void processMessage(Update update) {
		Message message = update.getMessage();
		Chat chat = this.chatService.getByChatId(update.getMessage().getChatId());

		BotCommand botCommand = null;
		if (chat == null) {
			chat = new Chat();
			chat.setChatId(message.getChatId());
			chat.setTelegramUser(message.getFrom().getUserName());
			chat.setSesionIniciada(false);
			chatService.updateChat(chat);
		}
		chat.setUpdate(update);

		if (message.getText() != null) {
			String text = message.getText();
			botCommand = getBotCommand(text);

			if (botCommand == null) {
				BotCommandEnum command = chat.getCurrentCommand();
				if (command != null) {
					botCommand = getBotCommand(chat.getCurrentCommand().command);
				}
			}
		}

		if (botCommand != null) {
			Invoker invoker = new Invoker(chat, botCommand);
			invoker.run();
		}
	}

	private void processCallback(Update update) {
		Chat chat = chatService.getByChatId(update.getCallbackQuery().getMessage().getChatId());

		if (chat == null) {
			return;
		}
		String text = null;
		if (chat.getCurrentCommand() != null) {
			text = chat.getCurrentCommand().command;
		}

		if (text != null) {
			BotCommand botCommand = getBotCommand(text);
			chat.setUpdate(update);
			Invoker invoker = new Invoker(chat, botCommand);
			invoker.run();
		}
	}

	private BotCommand getBotCommand(String text) {
		if (text.equalsIgnoreCase(BotCommandEnum.START.command)) {
			return new StartCommand(bot);
		} else if (text.equalsIgnoreCase(BotCommandEnum.LOGIN.command)) {
			return new LoginCommand(bot);
		} else if (text.equalsIgnoreCase(BotCommandEnum.BUSCAR_PROFESOR.command)) {
			return new BuscarProfesorCommand(bot);
		} else if (text.equalsIgnoreCase(BotCommandEnum.WEB.command)) {
			return new WebCommand(bot);
		} else if (text.equalsIgnoreCase(BotCommandEnum.QUIT.command)) {
			return new QuitCommand(bot);
		}

		return null;
	}
}
