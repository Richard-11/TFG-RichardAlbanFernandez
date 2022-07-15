package es.uva.inf.tutorias.rest.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uva.inf.tutorias.rest.exceptions.UsuarioBadRequestException;

@ControllerAdvice
public class UsuarioAdvice {

	@ResponseBody
	@ExceptionHandler(UsuarioBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String usuarioBadRequestHandler(UsuarioBadRequestException ex) {
		return ex.getMessage();
	}
}
