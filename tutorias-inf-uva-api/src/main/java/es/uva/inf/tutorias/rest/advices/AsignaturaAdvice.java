package es.uva.inf.tutorias.rest.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uva.inf.tutorias.rest.exceptions.AsignaturaBadRequestException;

@ControllerAdvice
public class AsignaturaAdvice {

	@ResponseBody
	@ExceptionHandler(AsignaturaBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String solicitudTutoriaBadRequestHandler(AsignaturaBadRequestException ex) {
		return ex.getMessage();
	}
}
