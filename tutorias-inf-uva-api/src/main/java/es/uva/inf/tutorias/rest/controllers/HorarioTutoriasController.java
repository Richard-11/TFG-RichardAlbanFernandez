package es.uva.inf.tutorias.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.exceptions.HorariosTutoriasNoValidosException;
import es.uva.inf.tutorias.business.domain.models.HorarioTutorias;
import es.uva.inf.tutorias.business.services.HorarioTutoriasService;
import es.uva.inf.tutorias.rest.exceptions.HorarioTutoriasBadRequestException;

@RestController
public class HorarioTutoriasController {

	@Autowired
	private HorarioTutoriasService horarioTutoriasService;

	@GetMapping(path = "/profesores/{identificador}/horarios", produces = MediaType.APPLICATION_JSON_VALUE)
	List<HorarioTutorias> getHorariosTutorias(@PathVariable String identificador) {
		return horarioTutoriasService.getHorariosTutorias(identificador);
	}

	@PostMapping(path = "/profesores/{identificador}/horarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	List<HorarioTutorias> nuevosHorariosTutorias(@PathVariable String identificador,
			@RequestBody List<HorarioTutorias> horarios) {
		try {
			return horarioTutoriasService.nuevosHorariosTutorias(identificador, horarios);
		} catch (HorariosTutoriasNoValidosException e) {
			throw new HorarioTutoriasBadRequestException(e.getMessage(), e);
		}
	}

}
