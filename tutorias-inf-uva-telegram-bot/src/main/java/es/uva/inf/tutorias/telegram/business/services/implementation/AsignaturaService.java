package es.uva.inf.tutorias.telegram.business.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Asignatura;
import es.uva.inf.tutorias.telegram.business.services.IAsignaturaService;
import es.uva.inf.tutorias.telegram.business.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class AsignaturaService implements IAsignaturaService {

	private final String ASIGNATURAS = "/asignaturas";
	private final String ASIGNATURAS_CODIGO = "/asignaturas/{codigo}";
	private final String USUARIOS_IDENTIFICADOR_ASIGNATURAS = "/usuarios/{identificador}/asignaturas";

	@Autowired
	private ApiWebClient apiWebClient;

	@Override
	public List<Asignatura> getAsignaturas(String identificadorEscuela) throws ApiWebClientException {
		try {
			Mono<List<Asignatura>> response = apiWebClient.get(USUARIOS_IDENTIFICADOR_ASIGNATURAS, identificadorEscuela)
					.bodyToMono(new ParameterizedTypeReference<List<Asignatura>>() {
					});

			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public List<Asignatura> getAsignaturas(Integer codigoTitulacion, Integer cursoId) throws ApiWebClientException {
		try {
			String url = ASIGNATURAS + "?codigoTitulacion=" + codigoTitulacion + "&cursoId=" + cursoId;
			Mono<List<Asignatura>> response = apiWebClient.get(url)
					.bodyToMono(new ParameterizedTypeReference<List<Asignatura>>() {
					});

			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public List<Asignatura> getAsignaturas(Integer codigoTitulacion, Integer cursoId, Integer mencionId)
			throws ApiWebClientException {
		try {
			String url = ASIGNATURAS + "?codigoTitulacion=" + codigoTitulacion + "&cursoId=" + cursoId + "&mencionId="
					+ mencionId;
			Mono<List<Asignatura>> response = apiWebClient.get(url)
					.bodyToMono(new ParameterizedTypeReference<List<Asignatura>>() {
					});

			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public List<Asignatura> getAsignaturas(String identificadorEscuela, String nombre) throws ApiWebClientException {
		try {
			String url = USUARIOS_IDENTIFICADOR_ASIGNATURAS + "?nombre=" + nombre;
			Mono<List<Asignatura>> response = apiWebClient.get(url, identificadorEscuela)
					.bodyToMono(new ParameterizedTypeReference<List<Asignatura>>() {
					});

			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public Asignatura getAsignatura(Integer codigoAsignatura) throws ApiWebClientException {
		try {
			return apiWebClient.get(ASIGNATURAS_CODIGO, codigoAsignatura).bodyToMono(Asignatura.class).block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

}
