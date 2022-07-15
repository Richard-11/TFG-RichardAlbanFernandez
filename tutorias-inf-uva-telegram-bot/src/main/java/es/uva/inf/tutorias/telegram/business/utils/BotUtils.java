package es.uva.inf.tutorias.telegram.business.utils;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import es.uva.inf.tutorias.telegram.business.domain.enums.TipoBusquedaProfesor;
import es.uva.inf.tutorias.telegram.business.domain.models.Asignatura;
import es.uva.inf.tutorias.telegram.business.domain.models.CursoAcademico;
import es.uva.inf.tutorias.telegram.business.domain.models.MencionTitulacion;
import es.uva.inf.tutorias.telegram.business.domain.models.Profesor;
import es.uva.inf.tutorias.telegram.business.domain.models.Titulacion;
import es.uva.inf.tutorias.telegram.business.domain.models.TutoriasInfUVaBot;

public class BotUtils {

	public static void sendMessage(TutoriasInfUVaBot sender, String text, Long chatId) {
		sendMessage(sender, text, chatId, null);
	}

	public static void sendLoginRequiredMessage(TutoriasInfUVaBot sender, Long chatId) {
		String text = "Para utilizar las funciones del bot es necesario que inicie sesión con el comando /login";
		sendMessage(sender, text, chatId, null);
	}

	public static void sendMessage(TutoriasInfUVaBot sender, String text, Long chatId, String parseMode) {
		SendMessage message = new SendMessage();
		message.setParseMode(parseMode);
		message.setText(text);
		message.setChatId(chatId.toString());

		sendMessage(sender, message);
	}

	public static void sendOptions(TutoriasInfUVaBot sender, String text, Titulacion[] titulaciones, Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < titulaciones.length; i++) {
			Titulacion titulacion = titulaciones[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(titulacion.getNombre());
			inlineButton.setCallbackData(titulacion.getCodigo().toString());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendOptions(TutoriasInfUVaBot sender, String text, CursoAcademico[] cursosAcademicos,
			Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < cursosAcademicos.length; i++) {
			CursoAcademico curso = cursosAcademicos[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(curso.getCurso());
			inlineButton.setCallbackData(curso.getId().toString());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendOptions(TutoriasInfUVaBot sender, String text, MencionTitulacion[] menciones, Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < menciones.length; i++) {
			MencionTitulacion mencion = menciones[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(mencion.getNombre());
			inlineButton.setCallbackData(mencion.getId().toString());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendOptions(TutoriasInfUVaBot sender, String text, Asignatura[] asignaturas, Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < asignaturas.length; i++) {
			Asignatura asignatura = asignaturas[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(String.format("%s (%s)", asignatura.getNombre(), asignatura.getAcronimo()));
			inlineButton.setCallbackData(asignatura.getCodigo().toString());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendOptions(TutoriasInfUVaBot sender, String text, Profesor[] profesores, Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < profesores.length; i++) {
			Profesor profesor = profesores[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(profesor.getNombre() + " " + profesor.getApellidos());
			inlineButton.setCallbackData(profesor.getIdentificador());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendOptionsTipoBusqueda(TutoriasInfUVaBot sender, String text, Long chatId) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (int i = 0; i < TipoBusquedaProfesor.values().length; i++) {
			TipoBusquedaProfesor tipoBusqueda = TipoBusquedaProfesor.values()[i];
			List<InlineKeyboardButton> row = new ArrayList<>();
			InlineKeyboardButton inlineButton = new InlineKeyboardButton();
			inlineButton.setText(tipoBusqueda.tipoBusqueda);
			inlineButton.setCallbackData(tipoBusqueda.id.toString());
			row.add(inlineButton);
			keyboard.add(row);
		}

		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

	public static void sendCallbackMessage(TutoriasInfUVaBot sender, Message message, String answer) {
		EditMessageText newMessage = new EditMessageText();
		newMessage.setChatId(message.getChatId().toString());
		newMessage.setMessageId(message.getMessageId());
		newMessage.setText(answer);
		newMessage.setParseMode(ParseMode.MARKDOWNV2);

		try {
			sender.execute(newMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private static void sendMessage(DefaultAbsSender sender, SendMessage message) {
		try {
			sender.execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public static void sendWebButton(TutoriasInfUVaBot sender, Long chatId, String text, String url) {
		InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		List<InlineKeyboardButton> keyboardRow = new ArrayList<>();
		InlineKeyboardButton keyboardButton = new InlineKeyboardButton();
		keyboardButton.setText(text);
		keyboardButton.setUrl(url);
		keyboardRow.add(keyboardButton);
		keyboard.add(keyboardRow);
		replyMarkup.setKeyboard(keyboard);

		SendMessage message = new SendMessage();
		message.setText(text);
		message.setChatId(chatId.toString());
		message.setReplyMarkup(replyMarkup);

		sendMessage(sender, message);
	}

}
