package es.uva.inf.tutorias.rest.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.exceptions.HoraFinMenorOIgualQueHoraInicioException;
import es.uva.inf.tutorias.business.domain.exceptions.TutoriaNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Tutoria;
import es.uva.inf.tutorias.business.services.TutoriaService;
import es.uva.inf.tutorias.rest.exceptions.TutoriaBadRequestException;

@RestController
public class TutoriaController {

	@Autowired
	private TutoriaService tutoriaService;

	@GetMapping(path = "/usuarios/{identificador}/tutorias/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Tutoria getTutoria(@PathVariable String identificador, @PathVariable Long id) {
		try {
			return tutoriaService.getTutoria(identificador, id);
		} catch (TutoriaNotFoundException e) {
			throw new TutoriaBadRequestException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/usuarios/{identificador}/tutorias", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Tutoria> getTutoriasAlumno(@PathVariable String identificador,
			@RequestParam(required = false) LocalDate fechaTutoria,
			@RequestParam(required = false) LocalTime horaInicio, @RequestParam(required = false) LocalTime horaFin,
			@RequestParam(required = false) Boolean cancelada, @RequestParam(required = false) Boolean grupal,
			@RequestParam(required = false) String idProfesor, @RequestParam(required = false) Integer codigoAsignatura,
			@RequestParam(required = false) boolean proximas, @RequestParam(required = false) boolean hoy) {
		if (proximas && hoy) {
			throw new TutoriaBadRequestException();
		}

		if (proximas) {
			return tutoriaService.getProximasTutorias(identificador);
		}

		if (hoy) {
			return tutoriaService.getTutoriasHoy(identificador);
		}
		
		if (horaInicio == null ^ horaFin == null) {
			throw new TutoriaBadRequestException();
		}

		if (horaInicio != null && horaFin != null && fechaTutoria == null) {
			throw new TutoriaBadRequestException();
		}

		try {
			return tutoriaService.getTutorias(identificador, fechaTutoria, horaInicio, horaFin, cancelada, grupal,
					idProfesor, codigoAsignatura);
		} catch (HoraFinMenorOIgualQueHoraInicioException e) {
			throw new TutoriaBadRequestException(e.getMessage(), e);
		}
	}

	@PutMapping(path = "/tutorias/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Tutoria cancelarTutoria(@PathVariable Long id, @RequestBody Tutoria tutoriaModificada) {
		if (tutoriaModificada == null) {
			throw new TutoriaBadRequestException();
		}

		if (tutoriaModificada.getMotivoCancelacion() == null || tutoriaModificada.getMotivoCancelacion().isEmpty()) {
			throw new TutoriaBadRequestException();
		}

		try {
			return tutoriaService.cancelarTutoria(id, tutoriaModificada.getMotivoCancelacion());
		} catch (TutoriaNotFoundException e) {
			throw new TutoriaBadRequestException();
		}
	}
}
