package es.uva.inf.tutorias.persistence.repositories;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.TutoriaDB;

@Repository
public interface TutoriaRepository extends JpaRepository<TutoriaDB, Long> {
	
	TutoriaDB findBySolicitudTutoriaAlumnoIdentificadorAndId(String identificador, Long id);

	TutoriaDB findBySolicitudTutoriaProfesorIdentificadorAndId(String identificador, Long id);

	@Query("SELECT t FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.alumno a JOIN st.profesor p WHERE a.identificador = :identificador AND (cast(:fechaTutoria as date) IS null OR st.fechaTutoria = :fechaTutoria) AND (:cancelada IS null OR t.cancelada = :cancelada) AND (:grupal IS null OR st.grupal = :grupal) AND (:idProfesor IS null OR p.identificador = :idProfesor) AND (:codigoAsignatura IS null OR st.asignatura.codigo = :codigoAsignatura) ORDER BY st.fechaSolicitud DESC")
	List<TutoriaDB> findTutoriasAlumnoBy(String identificador, Date fechaTutoria, Boolean cancelada, Boolean grupal, String idProfesor, Integer codigoAsignatura);

	@Query("SELECT t FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.profesor p WHERE p.identificador = :identificador AND (cast(:fechaTutoria as date) IS null OR st.fechaTutoria = :fechaTutoria) AND ((cast(:horaInicio as time) IS null AND cast(:horaFin as time) IS null) OR NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))) AND (:cancelada IS null OR t.cancelada = :cancelada) AND (:grupal IS null OR st.grupal = :grupal) AND (:codigoAsignatura IS null OR st.asignatura.codigo = :codigoAsignatura) ORDER BY st.fechaSolicitud DESC")
	List<TutoriaDB> findTutoriasProfesorBy(String identificador, Date fechaTutoria, LocalTime horaInicio,
			LocalTime horaFin, Boolean cancelada, Boolean grupal, Integer codigoAsignatura);

	@Query("SELECT t FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.alumno a WHERE a.identificador = :identificador AND cast((st.fechaTutoria + st.horaFin) as timestamp) >= cast(:fecha as timestamp) AND t.cancelada = false ORDER BY cast((st.fechaTutoria + st.horaInicio) as timestamp) ASC")
	List<TutoriaDB> findTutoriasAlumnoFechaTutoriaMayorOIgualQue(String identificador, Date fecha);

	@Query("SELECT t FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.profesor p WHERE p.identificador = :identificador AND cast((st.fechaTutoria + st.horaFin) as timestamp) >= cast(:fecha as timestamp) AND t.cancelada = false ORDER BY cast((st.fechaTutoria + st.horaInicio) as timestamp) ASC")
	List<TutoriaDB> findTutoriasProfesorFechaTutoriaMayorOIgualQue(String identificador, Date fecha);

	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.profesor p WHERE p.identificador = :idProfesor AND t.cancelada = false AND st.fechaTutoria = :fechaTutoria AND NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))")
	boolean existsTutoriasParaProfesorEnFechaIntervaloHoras(String idProfesor, Date fechaTutoria, LocalTime horaInicio,
			LocalTime horaFin);

	@Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TutoriaDB t JOIN t.solicitudTutoria st JOIN st.alumno a WHERE a.identificador = :idAlumno AND t.cancelada = false AND st.fechaTutoria = :fechaTutoria AND NOT ((st.horaInicio < :horaInicio AND st.horaFin <= :horaInicio) OR (st.horaInicio >= :horaFin AND st.horaFin > :horaFin))")
	boolean existsTutoriasParaAlumnoEnFechaIntervaloHoras(String idAlumno, Date fechaTutoria,
			LocalTime horaInicio, LocalTime horaFin);

}
