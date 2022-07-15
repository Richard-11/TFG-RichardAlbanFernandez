package es.uva.inf.tutorias.telegram.business.services;

import java.util.List;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Profesor;

public interface IProfesorService {

	List<Profesor> getProfesores(Integer codigoAsignatura) throws ApiWebClientException;

	Profesor getProfesor(String profesorId) throws ApiWebClientException;

}
