package es.uva.inf.tutorias.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.exceptions.AsignaturaNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Asignatura;
import es.uva.inf.tutorias.business.services.AsignaturaService;
import es.uva.inf.tutorias.rest.exceptions.AsignaturaBadRequestException;

@RestController
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturaService;
	
	@GetMapping(path = "/asignaturas/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
	Asignatura getAsignatura(@PathVariable Integer codigo) {
		try {
			return asignaturaService.getAsignatura(codigo);
		} catch (AsignaturaNotFoundException e) {
			throw new AsignaturaBadRequestException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/asignaturas", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Asignatura> getAsignaturas(@RequestParam Integer codigoTitulacion,
			@RequestParam(required = false) Integer cursoId, @RequestParam(required = false) Integer mencionId,
			@RequestParam(required = false, defaultValue = "") String nombre) {

		return asignaturaService.getAsignaturas(codigoTitulacion, cursoId, mencionId, nombre);
	}
	
	@GetMapping(path = "/usuarios/{identificador}/asignaturas", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Asignatura> getAsignaturasUsuario(@PathVariable String identificador, @RequestParam(required = false) String nombre) {
		return asignaturaService.getAsignaturasUsuario(identificador, nombre);
	}
}
