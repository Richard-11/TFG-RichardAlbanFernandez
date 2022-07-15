package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.models.Alumno;
import es.uva.inf.tutorias.business.domain.models.Asignatura;
import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.business.domain.models.SolicitudTutoria;
import es.uva.inf.tutorias.persistence.entities.AsignaturaDB;
import es.uva.inf.tutorias.persistence.entities.SolicitudTutoriaDB;

public class SolicitudTutoriaConverter {

	public static SolicitudTutoria convertToSolicitudTutoria(SolicitudTutoriaDB solicitudDB) {
		SolicitudTutoria solicitud = new SolicitudTutoria();
		solicitud.setId(solicitudDB.getId());
		solicitud.setAsunto(solicitudDB.getAsunto());
		solicitud.setComentarioAlumno(solicitudDB.getComentarioAlumno());
		solicitud.setFechaSolicitud(DateConverter.convertToLocalDateTime(solicitudDB.getFechaSolicitud()));
		solicitud.setFechaTutoria(DateConverter.convertToLocalDate(solicitudDB.getFechaTutoria()));
		solicitud.setHoraInicio(solicitudDB.getHoraInicio());
		solicitud.setHoraFin(solicitudDB.getHoraFin());
		solicitud.setGrupal(solicitudDB.getGrupal());
		solicitud.setMotivoRechazo(solicitudDB.getMotivoRechazo());
		solicitud.setRechazadaVistaPorAlumno(solicitudDB.getRechazadaVistaPorAlumno());
		solicitud.setUbicacionTutoria(solicitudDB.getUbicacionTutoria());
		solicitud.setComentarioProfesor(solicitudDB.getComentarioProfesor());
		solicitud.setPropuestaNuevoHorario(solicitudDB.getPropuestaNuevoHorario());
		solicitud.setEstadoSolicitud(
				EstadoSolicitudConverter.convertToEstadoSolicitud(solicitudDB.getEstadoSolicitud()));
		solicitud.setAlumno(AlumnoConverter.convertToAlumno(solicitudDB.getAlumno()));
		solicitud.setProfesor(ProfesorConverter.convertToProfesor(solicitudDB.getProfesor()));

		AsignaturaDB asignaturaDB = solicitudDB.getAsignatura();
		if (asignaturaDB != null) {
			solicitud.setAsignatura(AsignaturaConverter.convertToAsignatura(asignaturaDB));
		}

		return solicitud;
	}

	public static SolicitudTutoriaDB convertToSolicitudTutoriaDB(SolicitudTutoria solicitud) {
		SolicitudTutoriaDB solicitudDB = new SolicitudTutoriaDB();
		solicitudDB.setId(solicitud.getId());
		solicitudDB.setAsunto(solicitud.getAsunto());
		solicitudDB.setComentarioAlumno(solicitud.getComentarioAlumno());
		solicitudDB.setFechaSolicitud(DateConverter.convertToDate(solicitud.getFechaSolicitud()));
		solicitudDB.setFechaTutoria(DateConverter.convertToDate(solicitud.getFechaTutoria()));
		solicitudDB.setHoraInicio(solicitud.getHoraInicio());
		solicitudDB.setHoraFin(solicitud.getHoraFin());
		solicitudDB.setGrupal(solicitud.getGrupal());
		solicitudDB.setMotivoRechazo(solicitud.getMotivoRechazo());
		solicitudDB.setRechazadaVistaPorAlumno(solicitud.getRechazadaVistaPorAlumno());
		solicitudDB.setUbicacionTutoria(solicitud.getUbicacionTutoria());
		solicitudDB.setComentarioProfesor(solicitud.getComentarioProfesor());
		solicitudDB.setPropuestaNuevoHorario(solicitud.getPropuestaNuevoHorario());

		if (solicitud.getEstadoSolicitud() != null) {
			solicitudDB.setEstadoSolicitud(
					EstadoSolicitudConverter.convertToEstadoSolicitudDB(solicitud.getEstadoSolicitud()));
		}

		Asignatura asignatura = solicitud.getAsignatura();
		if (asignatura != null) {
			solicitudDB.setAsignatura(AsignaturaConverter.convertToAsignaturaDB(asignatura));
		}

		Alumno alumno = solicitud.getAlumno();
		if (alumno != null) {
			solicitudDB.setAlumno(AlumnoConverter.convertToAlumnoDB(alumno));
		}

		Profesor profesor = solicitud.getProfesor();
		if (profesor != null) {
			solicitudDB.setProfesor(ProfesorConverter.convertToProfesorDB(profesor));
		}

		return solicitudDB;
	}

	public static SolicitudTutoria convertToSolicitudTutoriaLight(SolicitudTutoriaDB solicitudDB) {
		SolicitudTutoria solicitud = new SolicitudTutoria();
		solicitud.setId(solicitudDB.getId());
		solicitud.setAsunto(solicitudDB.getAsunto());
		solicitud.setComentarioAlumno(solicitudDB.getComentarioAlumno());
		solicitud.setFechaSolicitud(DateConverter.convertToLocalDateTime(solicitudDB.getFechaSolicitud()));
		solicitud.setFechaTutoria(DateConverter.convertToLocalDate(solicitudDB.getFechaTutoria()));
		solicitud.setHoraInicio(solicitudDB.getHoraInicio());
		solicitud.setHoraFin(solicitudDB.getHoraFin());
		solicitud.setGrupal(solicitudDB.getGrupal());
		solicitud.setMotivoRechazo(solicitudDB.getMotivoRechazo());
		solicitud.setRechazadaVistaPorAlumno(solicitudDB.getRechazadaVistaPorAlumno());
		solicitud.setUbicacionTutoria(solicitudDB.getUbicacionTutoria());
		solicitud.setComentarioProfesor(solicitudDB.getComentarioProfesor());
		solicitud.setPropuestaNuevoHorario(solicitudDB.getPropuestaNuevoHorario());
		solicitud.setEstadoSolicitud(
				EstadoSolicitudConverter.convertToEstadoSolicitud(solicitudDB.getEstadoSolicitud()));
		solicitud.setAlumno(AlumnoConverter.convertToAlumnoLight(solicitudDB.getAlumno()));
		solicitud.setProfesor(ProfesorConverter.convertToProfesorLight(solicitudDB.getProfesor()));

		AsignaturaDB asignaturaDB = solicitudDB.getAsignatura();
		if (asignaturaDB != null) {
			solicitud.setAsignatura(AsignaturaConverter.convertToAsignaturaLight(asignaturaDB));
		}

		return solicitud;
	}
}
