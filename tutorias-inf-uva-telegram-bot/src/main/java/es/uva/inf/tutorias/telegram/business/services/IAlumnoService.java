package es.uva.inf.tutorias.telegram.business.services;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.exceptions.AuthenticationFailedException;
import es.uva.inf.tutorias.telegram.business.domain.models.Alumno;

public interface IAlumnoService {

	Alumno login(String identificador, String password) throws AuthenticationFailedException, ApiWebClientException;
	
}
