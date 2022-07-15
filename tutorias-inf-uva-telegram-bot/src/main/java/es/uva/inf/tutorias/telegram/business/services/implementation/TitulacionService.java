package es.uva.inf.tutorias.telegram.business.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.models.Titulacion;
import es.uva.inf.tutorias.telegram.business.services.ITitulacionService;
import es.uva.inf.tutorias.telegram.business.webclient.ApiWebClient;
import reactor.core.publisher.Mono;

@Service
public class TitulacionService implements ITitulacionService {

	private final String USUARIOS_IDENTIFICADOR_TITULACIONES = "/usuarios/{identificador}/titulaciones";
	private final String TITULACIONES_CODIGOTITULACION = "/titulaciones/{codigoTitulacion}";

	@Autowired
	private ApiWebClient apiWebClient;

	@Override
	public Titulacion getTitulacion(Integer codigoTitulacion) throws ApiWebClientException {
		try {
			return apiWebClient.get(TITULACIONES_CODIGOTITULACION, codigoTitulacion).bodyToMono(Titulacion.class)
					.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

	@Override
	public List<Titulacion> getTitulaciones(String identificadorEscuela) throws ApiWebClientException {
		try {
			Mono<List<Titulacion>> response = apiWebClient
					.get(USUARIOS_IDENTIFICADOR_TITULACIONES, identificadorEscuela)
					.bodyToMono(new ParameterizedTypeReference<List<Titulacion>>() {
					});
			return response.block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
		}
	}

}
