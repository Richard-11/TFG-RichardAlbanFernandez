package es.uva.inf.tutorias.telegram.business.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Profesor;
import es.uva.inf.tutorias.telegram.business.services.IProfesorService;
import es.uva.inf.tutorias.telegram.business.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class ProfesorService implements IProfesorService {

	private final String ASIGNATURAS_CODIGO_PROFESORES = "/asignaturas/{codigo}/profesores";
	private final String PROFESORES_IDENTIFICADOR = "/profesores/{identificador}";

	@Autowired
	private ApiWebClient apiWebClient;

	@Override
	public List<Profesor> getProfesores(Integer codigoAsignatura) throws ApiWebClientException {
		try {
			Mono<List<Profesor>> response = apiWebClient.get(ASIGNATURAS_CODIGO_PROFESORES, codigoAsignatura)
					.bodyToMono(new ParameterizedTypeReference<List<Profesor>>() {
					});
			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public Profesor getProfesor(String profesorId) throws ApiWebClientException {
		try {
			return apiWebClient.get(PROFESORES_IDENTIFICADOR, profesorId).bodyToMono(Profesor.class).block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

}
