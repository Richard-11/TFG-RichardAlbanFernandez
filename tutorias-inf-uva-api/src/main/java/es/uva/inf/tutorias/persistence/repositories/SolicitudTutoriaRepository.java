package es.uva.inf.tutorias.persistence.repositories;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.SolicitudTutoriaDB;

@Repository
public interface SolicitudTutoriaRepository extends JpaRepository<SolicitudTutoriaDB, Long> {
	
	SolicitudTutoriaDB findByAlumnoIdentificadorAndId(String identificador, Long id);

	SolicitudTutoriaDB findByProfesorIdentificadorAndId(String identificador, Long id);

	@Query("SELECT st FROM SolicitudTutoriaDB st JOIN st.alumno a JOIN st.profesor p JOIN st.estadoSolicitud es WHERE a.identificador = :identificador AND (:estadoSolicitudId IS null OR es.id = :estadoSolicitudId) AND (cast(:fechaTutoria as date) IS null OR st.fechaTutoria = :fechaTutoria) AND (:grupal IS null OR st.grupal = :grupal) AND (:idProfesor IS null OR p.identificador = :idProfesor) AND (:codigoAsignatura IS null OR st.asignatura.codigo = :codigoAsignatura) ORDER BY st.fechaSolicitud DESC")
	List<SolicitudTutoriaDB> findSolicitudesTutoriaAlumnoBy(String identificador, Short estadoSolicitudId, Date fechaTutoria, Boolean grupal, String idProfesor, Integer codigoAsignatura);

	@Query("SELECT st FROM SolicitudTutoriaDB st JOIN st.profesor p JOIN st.estadoSolicitud es WHERE p.identificador = :identificador AND (:estadoSolicitudId IS null OR es.id = :estadoSolicitudId) AND (cast(:fechaTutoria as date) IS null OR st.fechaTutoria = :fechaTutoria) AND ((cast(:horaInicio as time) IS null AND cast(:horaFin as time) IS null) OR NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))) AND (:grupal IS null OR st.grupal = :grupal) AND (:codigoAsignatura IS null OR st.asignatura.codigo = :codigoAsignatura) ORDER BY st.fechaSolicitud DESC")
	List<SolicitudTutoriaDB> findSolicitudesTutoriaProfesorBy(String identificador, Short estadoSolicitudId,
			Date fechaTutoria, LocalTime horaInicio, LocalTime horaFin, Boolean grupal, Integer codigoAsignatura);

	@Query("SELECT CASE WHEN COUNT(st) > 0 THEN true ELSE false END FROM SolicitudTutoriaDB st JOIN st.alumno a JOIN st.estadoSolicitud es WHERE a.identificador = :idAlumno AND es.id = 1 AND st.fechaTutoria = :fechaTutoria AND NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))")
	boolean existsSolicitudesPendientesParaAlumnoEnFechaIntervaloHoras(String idAlumno, Date fechaTutoria,
			LocalTime horaInicio, LocalTime horaFin);

	@Query("SELECT CASE WHEN COUNT(st) > 0 THEN true ELSE false END FROM SolicitudTutoriaDB st JOIN st.profesor p JOIN st.estadoSolicitud es WHERE p.identificador = :idProfesor AND es.id = 2 AND st.fechaTutoria = :fechaTutoria AND NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))")
	boolean existsSolicitudesAceptadasParaProfesorEnFechaIntervaloHoras(String idProfesor, Date fechaTutoria,
			LocalTime horaInicio, LocalTime horaFin);
	
	@Query("SELECT st FROM SolicitudTutoriaDB st JOIN st.estadoSolicitud es WHERE es.id = 1 AND cast(st.fechaTutoria + st.horaInicio as timestamp) < CURRENT_TIMESTAMP")
	List<SolicitudTutoriaDB> findSolicitudesPendientesCaducadas();
}
