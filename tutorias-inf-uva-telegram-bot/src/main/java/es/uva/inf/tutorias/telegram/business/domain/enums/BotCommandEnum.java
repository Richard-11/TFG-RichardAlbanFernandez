package es.uva.inf.tutorias.telegram.business.domain.enums;

public enum BotCommandEnum {
	START("/start"),
	LOGIN("/login"),
	BUSCAR_PROFESOR("/buscar_profesor"),
	WEB("/web"),
	QUIT("/quit");
	
	public String command;
	
	private BotCommandEnum(String command) {
		this.command = command;
	}
}
