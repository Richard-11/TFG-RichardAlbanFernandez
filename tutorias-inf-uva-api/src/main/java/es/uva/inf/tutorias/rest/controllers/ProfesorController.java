package es.uva.inf.tutorias.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.business.services.ProfesorService;

@RestController
public class ProfesorController {

	@Autowired
	private ProfesorService profesorService;
	
	@GetMapping(path = "/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Profesor> getProfesores(@RequestParam(required = false, defaultValue = "") String nombre) {
		return profesorService.getProfesores(nombre);
	}

	@GetMapping(path = "/profesores/{identificador}", produces = MediaType.APPLICATION_JSON_VALUE)
	Profesor getProfesor(@PathVariable String identificador) {
		return profesorService.getProfesor(identificador);
	}
	
	@GetMapping(path = "/asignaturas/{codigo}/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Profesor> getProfesoresAsignatura(@PathVariable Integer codigo) {
		return profesorService.getProfesoresAsignatura(codigo);
	}
	
	@GetMapping(path = "/titulaciones/{codigo}/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Profesor> getProfesoresTitulacion(@PathVariable Integer codigo) {
		return profesorService.getProfesoresTitulacion(codigo);
	}
}
