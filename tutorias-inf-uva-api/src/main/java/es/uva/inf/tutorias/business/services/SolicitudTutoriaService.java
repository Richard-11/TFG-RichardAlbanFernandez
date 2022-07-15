package es.uva.inf.tutorias.business.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uva.inf.tutorias.business.domain.converters.DateConverter;
import es.uva.inf.tutorias.business.domain.converters.SolicitudTutoriaConverter;
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
import es.uva.inf.tutorias.persistence.entities.AlumnoDB;
import es.uva.inf.tutorias.persistence.entities.ProfesorDB;
import es.uva.inf.tutorias.persistence.entities.SolicitudTutoriaDB;
import es.uva.inf.tutorias.persistence.entities.UsuarioDB;
import es.uva.inf.tutorias.persistence.repositories.SolicitudTutoriaRepository;
import es.uva.inf.tutorias.persistence.repositories.UsuarioRepository;

@Service
public class SolicitudTutoriaService {

	@Autowired
	private TutoriaService tutoriaService;

	@Autowired
	private SolicitudTutoriaRepository solicitudTutoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public SolicitudTutoria getSolicitudTutoria(String identificador, Long id)
			throws SolicitudTutoriaNotFoundException {
		SolicitudTutoriaDB solicitudDB = null;
		UsuarioDB usuario = usuarioRepository.findByIdentificador(identificador);

		if (usuario instanceof ProfesorDB) {
			solicitudDB = solicitudTutoriaRepository.findByProfesorIdentificadorAndId(identificador, id);

			if (solicitudDB == null) {
				throw new SolicitudTutoriaNotFoundException();
			}
		}

		if (usuario instanceof AlumnoDB) {
			solicitudDB = solicitudTutoriaRepository.findByAlumnoIdentificadorAndId(identificador, id);

			if (solicitudDB == null) {
				throw new SolicitudTutoriaNotFoundException();
			}

			if (solicitudDB.getEstadoSolicitud().getId() == EstadoSolicitud.RECHAZADA.id
					&& !solicitudDB.getRechazadaVistaPorAlumno()) {
				solicitudDB.setRechazadaVistaPorAlumno(true);
				solicitudDB = solicitudTutoriaRepository.save(solicitudDB);
			}
		}

		return SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudDB);
	}

	public List<SolicitudTutoria> getSolicitudesTutoria(String identificador, Short estadoSolicitudId,
			LocalDate fechaTutoria, LocalTime horaInicio, LocalTime horaFin, Boolean grupal, String idProfesor,
			Integer codigoAsignatura) throws HoraFinMenorOIgualQueHoraInicioException {
		List<SolicitudTutoriaDB> solicitudesDB = new ArrayList<>();
		UsuarioDB usuario = usuarioRepository.findByIdentificador(identificador);

		if (usuario instanceof ProfesorDB) {
			if (horaInicio != null && horaFin != null && horaFin.compareTo(horaInicio) <= 0) {
				throw new HoraFinMenorOIgualQueHoraInicioException();
			}

			solicitudesDB = solicitudTutoriaRepository.findSolicitudesTutoriaProfesorBy(identificador,
					estadoSolicitudId, DateConverter.convertToDate(fechaTutoria), horaInicio, horaFin, grupal,
					codigoAsignatura);
		}

		if (usuario instanceof AlumnoDB) {
			solicitudesDB = solicitudTutoriaRepository.findSolicitudesTutoriaAlumnoBy(identificador, estadoSolicitudId,
					DateConverter.convertToDate(fechaTutoria), grupal, idProfesor, codigoAsignatura);
		}

		return solicitudesDB.stream()
				.map(solicitudDB -> SolicitudTutoriaConverter.convertToSolicitudTutoriaLight(solicitudDB))
				.collect(Collectors.toList());
	}

	@Transactional
	public SolicitudTutoria nuevaSolicitudTutoria(SolicitudTutoria nuevaSolicitud)
			throws ExistenTutoriasEnIntervaloParaProfesorException, HoraFinMenorOIgualQueHoraInicioException,
			SolicitudesPendientesEnIntervaloException, ExistenTutoriasEnIntervaloParaAlumnoException {
		if (nuevaSolicitud.getHoraFin().compareTo(nuevaSolicitud.getHoraInicio()) <= 0) {
			throw new HoraFinMenorOIgualQueHoraInicioException();
		}

		if (solicitudTutoriaRepository.existsSolicitudesPendientesParaAlumnoEnFechaIntervaloHoras(
				nuevaSolicitud.getAlumno().getIdentificador(),
				DateConverter.convertToDate(nuevaSolicitud.getFechaTutoria()), nuevaSolicitud.getHoraInicio(),
				nuevaSolicitud.getHoraFin())) {
			throw new SolicitudesPendientesEnIntervaloException();
		}

		if (tutoriaService.existenTutoriasParaAlumnoEnFechaIntervaloHoras(nuevaSolicitud.getAlumno().getIdentificador(),
				nuevaSolicitud.getFechaTutoria(), nuevaSolicitud.getHoraInicio(), nuevaSolicitud.getHoraFin())) {
			throw new ExistenTutoriasEnIntervaloParaAlumnoException();
		}

		if (tutoriaService.existenTutoriasParaProfesorEnFechaIntervaloHoras(
				nuevaSolicitud.getProfesor().getIdentificador(), nuevaSolicitud.getFechaTutoria(),
				nuevaSolicitud.getHoraInicio(), nuevaSolicitud.getHoraFin())) {
			throw new ExistenTutoriasEnIntervaloParaProfesorException();
		}

		nuevaSolicitud.setEstadoSolicitud(EstadoSolicitud.PENDIENTE);
		nuevaSolicitud.setPropuestaNuevoHorario(false);
		nuevaSolicitud.setRechazadaVistaPorAlumno(false);
		SolicitudTutoriaDB solicitudDB = SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(nuevaSolicitud);
		return SolicitudTutoriaConverter
				.convertToSolicitudTutoria(solicitudTutoriaRepository.save(solicitudDB));
	}

	@Transactional
	public SolicitudTutoria cancelarSolicitudTutoria(Long id) throws SolicitudTutoriaNotFoundException,
			SolicitudTutoriaNoPendienteException, SolicitudTutoriaNoPendienteONoAceptadaException {
		try {
			SolicitudTutoria solicitudTutoria = SolicitudTutoriaConverter
					.convertToSolicitudTutoria(solicitudTutoriaRepository.findById(id).get());

			if (solicitudTutoria.getEstadoSolicitud() == EstadoSolicitud.CONFIRMADA
					|| solicitudTutoria.getEstadoSolicitud() == EstadoSolicitud.RECHAZADA
					|| solicitudTutoria.getEstadoSolicitud() == EstadoSolicitud.CANCELADA) {
				throw new SolicitudTutoriaNoPendienteONoAceptadaException(solicitudTutoria.getEstadoSolicitud());
			}

			solicitudTutoria.setEstadoSolicitud(EstadoSolicitud.CANCELADA);
			return SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudTutoriaRepository
					.save(SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(solicitudTutoria)));
		} catch (NoSuchElementException e) {
			throw new SolicitudTutoriaNotFoundException();
		}
	}

	@Transactional
	public SolicitudTutoria aceptarSolicitudTutoria(Long id, SolicitudTutoria solicitudModificada)
			throws SolicitudTutoriaNotFoundException, SolicitudTutoriaNoPendienteException,
			ExistenTutoriasEnIntervaloParaProfesorException,
			ExistenSolicitudesAceptadasEnIntervaloParaProfesorException {
		try {
			SolicitudTutoria solicitudTutoria = SolicitudTutoriaConverter
					.convertToSolicitudTutoria(solicitudTutoriaRepository.findById(id).get());

			if (solicitudTutoria.getEstadoSolicitud() != EstadoSolicitud.PENDIENTE) {
				throw new SolicitudTutoriaNoPendienteException(solicitudTutoria.getEstadoSolicitud());
			}

			if (solicitudModificada.getPropuestaNuevoHorario()) {
				solicitudTutoria.setPropuestaNuevoHorario(solicitudModificada.getPropuestaNuevoHorario());
				solicitudTutoria.setHoraInicio(solicitudModificada.getHoraInicio());
				solicitudTutoria.setHoraFin(solicitudModificada.getHoraFin());
			}

			if (tutoriaService.existenTutoriasParaProfesorEnFechaIntervaloHoras(
					solicitudTutoria.getProfesor().getIdentificador(), solicitudTutoria.getFechaTutoria(),
					solicitudTutoria.getHoraInicio(), solicitudTutoria.getHoraFin())) {
				throw new ExistenTutoriasEnIntervaloParaProfesorException();
			}

			if (solicitudTutoriaRepository.existsSolicitudesAceptadasParaProfesorEnFechaIntervaloHoras(
					solicitudTutoria.getProfesor().getIdentificador(),
					DateConverter.convertToDate(solicitudTutoria.getFechaTutoria()), solicitudTutoria.getHoraInicio(),
					solicitudTutoria.getHoraFin())) {
				throw new ExistenSolicitudesAceptadasEnIntervaloParaProfesorException();
			}

			solicitudTutoria.setUbicacionTutoria(solicitudModificada.getUbicacionTutoria());
			solicitudTutoria.setComentarioProfesor(solicitudModificada.getComentarioProfesor());
			solicitudTutoria.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
			return SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudTutoriaRepository
					.save(SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(solicitudTutoria)));
		} catch (NoSuchElementException e) {
			throw new SolicitudTutoriaNotFoundException();
		}
	}

	@Transactional
	public SolicitudTutoria rechazarSolicitudTutoria(Long id, String motivoRechazo)
			throws SolicitudTutoriaNotFoundException, SolicitudTutoriaNoPendienteException {
		try {
			SolicitudTutoria solicitudTutoria = SolicitudTutoriaConverter
					.convertToSolicitudTutoria(solicitudTutoriaRepository.findById(id).get());

			if (solicitudTutoria.getEstadoSolicitud() != EstadoSolicitud.PENDIENTE) {
				throw new SolicitudTutoriaNoPendienteException(solicitudTutoria.getEstadoSolicitud());
			}

			solicitudTutoria.setMotivoRechazo(motivoRechazo);
			solicitudTutoria.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
			return SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudTutoriaRepository
					.save(SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(solicitudTutoria)));
		} catch (NoSuchElementException e) {
			throw new SolicitudTutoriaNotFoundException();
		}
	}

	@Transactional
	public SolicitudTutoria confirmarSolicitudTutoria(Long id) throws SolicitudTutoriaNotFoundException,
			SolicitudTutoriaNoAceptadaException, ExistenTutoriasEnIntervaloParaProfesorException {
		try {
			SolicitudTutoria solicitudTutoria = SolicitudTutoriaConverter
					.convertToSolicitudTutoria(solicitudTutoriaRepository.findById(id).get());

			if (solicitudTutoria.getEstadoSolicitud() != EstadoSolicitud.ACEPTADA) {
				throw new SolicitudTutoriaNoAceptadaException(solicitudTutoria.getEstadoSolicitud());
			}

			if (tutoriaService.existenTutoriasParaProfesorEnFechaIntervaloHoras(
					solicitudTutoria.getProfesor().getIdentificador(), solicitudTutoria.getFechaTutoria(),
					solicitudTutoria.getHoraInicio(), solicitudTutoria.getHoraFin())) {
				throw new ExistenTutoriasEnIntervaloParaProfesorException();
			}

			solicitudTutoria.setEstadoSolicitud(EstadoSolicitud.CONFIRMADA);
			SolicitudTutoriaDB solicitudDB = solicitudTutoriaRepository
					.save(SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(solicitudTutoria));
			tutoriaService.crearTutoria(solicitudTutoria.getId()).getSolicitudTutoria();
			return SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudDB);
		} catch (NoSuchElementException e) {
			throw new SolicitudTutoriaNotFoundException();
		}
	}

	@Transactional
	public List<SolicitudTutoria> rechazarSolicitudesPendientesCaducadas() {
		List<SolicitudTutoria> solicitudes = solicitudTutoriaRepository.findSolicitudesPendientesCaducadas().stream()
				.map(tutoriaDB -> SolicitudTutoriaConverter.convertToSolicitudTutoria(tutoriaDB))
				.collect(Collectors.toList());

		solicitudes.stream().forEach(solicitud -> {
			solicitud.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
			solicitud.setMotivoRechazo("Solicitud rechazada automáticamente. Motivo: Solicitud caducada");
		});

		return solicitudTutoriaRepository
				.saveAll(solicitudes.stream()
						.map(solicitud -> SolicitudTutoriaConverter.convertToSolicitudTutoriaDB(solicitud))
						.collect(Collectors.toList()))
				.stream().map(solicitudDB -> SolicitudTutoriaConverter.convertToSolicitudTutoria(solicitudDB))
				.collect(Collectors.toList());
	}

}