package es.uva.inf.tutorias.rest.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.uva.inf.tutorias.rest.exceptions.SolicitudTutoriaBadRequestException;

@ControllerAdvice
public class SolicitudTutoriaAdvice {

	@ResponseBody
	@ExceptionHandler(SolicitudTutoriaBadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String solicitudTutoriaBadRequestHandler(SolicitudTutoriaBadRequestException ex) {
		return ex.getMessage();
	}
}
