package es.uva.inf.tutorias.telegram.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.telegram.persistence.entities.ChatDB;

@Repository
public interface ChatRepository extends JpaRepository<ChatDB, Long> {

	public ChatDB findByChatId(Long chatId);

	public Long deleteByChatId(Long chatId);

}
