package es.uva.inf.tutorias.telegram.business.services.implementation;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.telegram.business.domain.converters.ChatConverter;
import es.uva.inf.tutorias.telegram.business.domain.models.Chat;
import es.uva.inf.tutorias.telegram.business.services.IChatService;
import es.uva.inf.tutorias.telegram.persistence.repositories.ChatRepository;

@Service
public class ChatService implements IChatService {

	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Chat getByChatId(Long chatId) {
		if (chatId == null) {
			throw new IllegalArgumentException("chatId null");
		}

		return ChatConverter.convertToChat(chatRepository.findByChatId(chatId));
	}

	@Override
	@Transactional
	public Chat updateChat(Chat chat) {
		if (chat == null) {
			throw new IllegalArgumentException("chat null");
		}

		return ChatConverter.convertToChat(chatRepository.save(ChatConverter.convertToChatDB(chat)));
	}

	@Override
	@Transactional
	public void deleteChat(Chat chat) {
		if (chat == null) {
			throw new IllegalArgumentException("chat null");
		}

		chatRepository.deleteByChatId(chat.getChatId());
	}
}
