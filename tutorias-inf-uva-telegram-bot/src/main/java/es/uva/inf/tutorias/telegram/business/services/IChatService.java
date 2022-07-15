package es.uva.inf.tutorias.telegram.business.services;

import es.uva.inf.tutorias.telegram.business.domain.models.Chat;

public interface IChatService {

	public Chat getByChatId(Long chatId);

	public Chat updateChat(Chat chat);

	public void deleteChat(Chat chat);
	
}
