package es.uva.inf.tutorias.telegram.business.domain.models;

public class Invoker {
	
	private Chat chatInvoker;
	private BotCommand botCommand;
	
	public Invoker(Chat chatInvoker, BotCommand botCommand) {
		this.chatInvoker = chatInvoker;
		this.botCommand = botCommand;
	}
	
	public void run() {
		botCommand.execute(chatInvoker);
	}

	public Chat getChatInvoker() {
		return chatInvoker;
	}

	public void setChatInvoker(Chat chatInvoker) {
		this.chatInvoker = chatInvoker;
	}

	public BotCommand getBotCommand() {
		return botCommand;
	}

	public void setBotCommand(BotCommand botCommand) {
		this.botCommand = botCommand;
	}
	
}
