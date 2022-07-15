package es.uva.inf.tutorias.telegram.business.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import es.uva.inf.tutorias.telegram.business.domain.exceptions.ApiWebClientException;
import es.uva.inf.tutorias.telegram.business.domain.exceptions.AuthenticationFailedException;
import es.uva.inf.tutorias.telegram.business.domain.models.Alumno;
import es.uva.inf.tutorias.telegram.business.services.IAlumnoService;
import es.uva.inf.tutorias.telegram.business.webclient.ApiWebClient;

@Service
public class AlumnoService implements IAlumnoService {

	private final String LOGIN = "/login/{identificador}";

	@Autowired
	private ApiWebClient apiWebClient;

	@Override
	public Alumno login(String identificador, String password)
			throws AuthenticationFailedException, ApiWebClientException {
		try {
			return apiWebClient.login(LOGIN, identificador, password).bodyToMono(Alumno.class).block();
		} catch (WebClientRequestException e) {
			throw new ApiWebClientException(e);
		} catch (WebClientResponseException e) {
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new AuthenticationFailedException();
			} else {
				throw new ApiWebClientException(e.getStatusCode(), e.getResponseBodyAsString());
			}
		}

	}

}
