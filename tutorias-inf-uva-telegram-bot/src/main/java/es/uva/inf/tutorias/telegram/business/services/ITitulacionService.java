package es.uva.inf.tutorias.telegram.business.services;

import java.util.List;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Titulacion;

public interface ITitulacionService {

	Titulacion getTitulacion(Integer codigoTitulacion) throws ApiWebClientException;

	List<Titulacion> getTitulaciones(String identificadorEscuela) throws ApiWebClientException;

}
