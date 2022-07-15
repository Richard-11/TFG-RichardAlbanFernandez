package es.uva.inf.tutorias.telegram.business.services;

import java.util.List;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Asignatura;

public interface IAsignaturaService {

	List<Asignatura> getAsignaturas(String identificadorEscuela) throws ApiWebClientException;

	List<Asignatura> getAsignaturas(Integer codigoTitulacion, Integer cursoId) throws ApiWebClientException;

	List<Asignatura> getAsignaturas(Integer codigoTitulacion, Integer cursoId, Integer mencionId)
			throws ApiWebClientException;

	List<Asignatura> getAsignaturas(String identificadorEscuela, String nombreAsignatura) throws ApiWebClientException;

	Asignatura getAsignatura(Integer codigoAsignatura) throws ApiWebClientException;

}
