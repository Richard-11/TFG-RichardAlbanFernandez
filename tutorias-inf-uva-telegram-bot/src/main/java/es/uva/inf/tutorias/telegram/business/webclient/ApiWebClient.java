package es.uva.inf.tutorias.telegram.business.webclient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import es.uva.inf.tutorias.telegram.configuration.RestClientConfiguration;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class ApiWebClient {

	@Autowired
	private RestClientConfiguration restClientConfiguration;

	public ResponseSpec login(String url, String identificador, String authorization)
			throws WebClientRequestException, WebClientResponseException {
		return webClient().post().uri(url, identificador).header(HttpHeaders.AUTHORIZATION, authorization).retrieve();
	}

	public ResponseSpec get(String url, Object... params) throws WebClientRequestException, WebClientResponseException {
		return webClient().get().uri(url, params).retrieve();
	}

	public ResponseSpec post(String url, Object... params)
			throws WebClientRequestException, WebClientResponseException {
		return webClient().post().uri(url, params).retrieve();
	}

	private WebClient webClient() {
		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.responseTimeout(Duration.ofMillis(5000)).doOnConnected(
						connection -> connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

		WebClient client = WebClient.builder().baseUrl(restClientConfiguration.getUrl())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.clientConnector(new ReactorClientHttpConnector(httpClient)).build();

		return client;
	}
}
