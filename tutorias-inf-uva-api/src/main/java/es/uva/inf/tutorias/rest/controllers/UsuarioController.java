package es.uva.inf.tutorias.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.exceptions.AuthenticationFailedException;
import es.uva.inf.tutorias.business.domain.models.Usuario;
import es.uva.inf.tutorias.business.services.UsuarioService;
import es.uva.inf.tutorias.rest.exceptions.UsuarioBadRequestException;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(path = "/login/{identificador}", produces = MediaType.APPLICATION_JSON_VALUE)
	Usuario login(@PathVariable String identificador, @RequestHeader("authorization") String password) {
		try {
			return usuarioService.login(identificador, password);
		} catch (AuthenticationFailedException e) {
			throw new UsuarioBadRequestException(e.getMessage(), e);
		}
	}
}
