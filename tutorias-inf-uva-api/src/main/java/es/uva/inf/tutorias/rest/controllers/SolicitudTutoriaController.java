package es.uva.inf.tutorias.rest.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.enums.EstadoSolicitud;
import es.uva.inf.tutorias.business.domain.exceptions.ExistenSolicitudesAceptadasEnIntervaloParaProfesorException;
import es.uva.inf.tutorias.business.domain.exceptions.ExistenTutoriasEnIntervaloParaAlumnoException;
import es.uva.inf.tutorias.business.domain.exceptions.ExistenTutoriasEnIntervaloParaProfesorException;
import es.uva.inf.tutorias.business.domain.exceptions.HoraFinMenorOIgualQueHoraInicioException;
import es.uva.inf.tutorias.business.domain.exceptions.SolicitudTutoriaNoAceptadaException;
import es.uva.inf.tutorias.business.domain.exceptions.SolicitudTutoriaNoPendienteException;
import es.uva.inf.tutorias.business.domain.exceptions.SolicitudTutoriaNoPendienteONoAceptadaException;
import es.uva.inf.tutorias.business.domain.exceptions.SolicitudTutoriaNotFoundException;
import es.uva.inf.tutorias.business.domain.exceptions.SolicitudesPendientesEnIntervaloException;
import es.uva.inf.tutorias.business.domain.models.SolicitudTutoria;
import es.uva.inf.tutorias.business.services.SolicitudTutoriaService;
import es.uva.inf.tutorias.rest.exceptions.SolicitudTutoriaBadRequestException;

@RestController
public class SolicitudTutoriaController {

	@Autowired
	private SolicitudTutoriaService solicitudTutoriaService;

	@PostMapping(path = "/solicitudes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	SolicitudTutoria nuevaSolicitudTutoria(@RequestBody SolicitudTutoria nuevaSolicitud) {
		try {
			return solicitudTutoriaService.nuevaSolicitudTutoria(nuevaSolicitud);
		} catch (HoraFinMenorOIgualQueHoraInicioException | SolicitudesPendientesEnIntervaloException
				| ExistenTutoriasEnIntervaloParaProfesorException | ExistenTutoriasEnIntervaloParaAlumnoException e) {
			throw new SolicitudTutoriaBadRequestException(e.getMessage(), e);
		}
	}

	@PutMapping(path = "/solicitudes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	SolicitudTutoria modificarSolicitudTutoria(@PathVariable Long id,
			@RequestBody SolicitudTutoria solicitudModificada) {
		try {
			if (solicitudModificada != null && solicitudModificada.getEstadoSolicitud() != null) {
				if (solicitudModificada.getEstadoSolicitud() == EstadoSolicitud.CANCELADA) {
					return solicitudTutoriaService.cancelarSolicitudTutoria(id);
				}

				if (solicitudModificada.getEstadoSolicitud() == EstadoSolicitud.ACEPTADA) {
					return solicitudTutoriaService.aceptarSolicitudTutoria(id, solicitudModificada);
				}

				if (solicitudModificada.getEstadoSolicitud() == EstadoSolicitud.RECHAZADA) {
					return solicitudTutoriaService.rechazarSolicitudTutoria(id, solicitudModificada.getMotivoRechazo());
				}

				if (solicitudModificada.getEstadoSolicitud() == EstadoSolicitud.CONFIRMADA) {
					return solicitudTutoriaService.confirmarSolicitudTutoria(id);
				}
			}

			throw new SolicitudTutoriaBadRequestException();
		} catch (SolicitudTutoriaNotFoundException | SolicitudTutoriaNoPendienteException
				| SolicitudTutoriaNoAceptadaException | SolicitudTutoriaNoPendienteONoAceptadaException
				| ExistenTutoriasEnIntervaloParaProfesorException
				| ExistenSolicitudesAceptadasEnIntervaloParaProfesorException e) {
			throw new SolicitudTutoriaBadRequestException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/usuarios/{identificador}/solicitudes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	SolicitudTutoria getSolicitudTutoria(@PathVariable String identificador, @PathVariable Long id) {
		try {
			return solicitudTutoriaService.getSolicitudTutoria(identificador, id);
		} catch (SolicitudTutoriaNotFoundException e) {
			throw new SolicitudTutoriaBadRequestException(e.getMessage(), e);
		}
	}

	@GetMapping(path = "/usuarios/{identificador}/solicitudes", produces = MediaType.APPLICATION_JSON_VALUE)
	List<SolicitudTutoria> getSolicitudesTutoria(@PathVariable String identificador,
			@RequestParam(required = false) Short estadoSolicitudId,
			@RequestParam(required = false) LocalDate fechaTutoria,
			@RequestParam(required = false) LocalTime horaInicio, @RequestParam(required = false) LocalTime horaFin,
			@RequestParam(required = false) Boolean grupal, @RequestParam(required = false) String idProfesor,
			@RequestParam(required = false) Integer codigoAsignatura) {
		if (horaInicio == null ^ horaFin == null) {
			throw new SolicitudTutoriaBadRequestException();
		}

		if (horaInicio != null && horaFin != null && fechaTutoria == null) {
			throw new SolicitudTutoriaBadRequestException();
		}

		try {
			return solicitudTutoriaService.getSolicitudesTutoria(identificador, estadoSolicitudId, fechaTutoria,
					horaInicio, horaFin, grupal, idProfesor, codigoAsignatura);
		} catch (HoraFinMenorOIgualQueHoraInicioException e) {
			throw new SolicitudTutoriaBadRequestException(e.getMessage(), e);
		}
	}
}
