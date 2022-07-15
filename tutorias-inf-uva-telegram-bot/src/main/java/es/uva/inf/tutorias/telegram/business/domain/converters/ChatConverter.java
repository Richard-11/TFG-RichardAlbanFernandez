package es.uva.inf.tutorias.telegram.business.domain.converters;

import es.uva.inf.tutorias.telegram.business.domain.models.Chat;
import es.uva.inf.tutorias.telegram.persistence.entities.ChatDB;

public class ChatConverter {

	public static Chat convertToChat(ChatDB chatDB) {
		if (chatDB == null) {
			return null;
		}

		Chat chat = new Chat();
		chat.setId(chatDB.getId());;
		chat.setChatId(chatDB.getChatId());
		chat.setTelegramUser(chatDB.getTelegramUser());
		chat.setIdentificadorEscuela(chatDB.getIdentificadorEscuela());
		chat.setNombreCompletoAlumno(chatDB.getNombreCompletoAlumno());
		chat.setSesionIniciada(chatDB.getSesionIniciada());
		chat.setCodigoTitulacion(chatDB.getCodigoTitulacion());
		chat.setCursoId(chatDB.getCursoId());
		chat.setMencionId(chatDB.getMencionId());
		chat.setCodigoAsignatura(chatDB.getCodigoAsignatura());
		chat.setProfesorId(chatDB.getProfesorId());
		chat.setNombreAsignatura(chatDB.getNombreAsignatura());
		chat.setCurrentCommand(chatDB.getCurrentCommand());
		chat.setPasoLogin(chatDB.getPasoLogin());
		chat.setTipoBusquedaProfesor(chatDB.getTipoBusquedaProfesor());
		chat.setPasoBuscarProfesor(chatDB.getPasoBuscarProfesor());
		
		return chat;
	}

	public static ChatDB convertToChatDB(Chat chat) {
		ChatDB chatDB = new ChatDB();
		chatDB.setId(chat.getId());;
		chatDB.setChatId(chat.getChatId());
		chatDB.setTelegramUser(chat.getTelegramUser());
		chatDB.setIdentificadorEscuela(chat.getIdentificadorEscuela());
		chatDB.setNombreCompletoAlumno(chat.getNombreCompletoAlumno());
		chatDB.setSesionIniciada(chat.getSesionIniciada());
		chatDB.setCodigoTitulacion(chat.getCodigoTitulacion());
		chatDB.setCursoId(chat.getCursoId());
		chatDB.setMencionId(chat.getMencionId());
		chatDB.setCodigoAsignatura(chat.getCodigoAsignatura());
		chatDB.setProfesorId(chat.getProfesorId());
		chatDB.setNombreAsignatura(chat.getNombreAsignatura());
		chatDB.setCurrentCommand(chat.getCurrentCommand());
		chatDB.setPasoLogin(chat.getPasoLogin());
		chatDB.setTipoBusquedaProfesor(chat.getTipoBusquedaProfesor());
		chatDB.setPasoBuscarProfesor(chat.getPasoBuscarProfesor());
		
		return chatDB;
	}
}
