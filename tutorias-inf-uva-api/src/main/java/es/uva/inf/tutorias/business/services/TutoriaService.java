package es.uva.inf.tutorias.business.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uva.inf.tutorias.business.domain.converters.DateConverter;
import es.uva.inf.tutorias.business.domain.converters.TutoriaConverter;
import es.uva.inf.tutorias.business.domain.exceptions.HoraFinMenorOIgualQueHoraInicioException;
import es.uva.inf.tutorias.business.domain.exceptions.TutoriaNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Tutoria;
import es.uva.inf.tutorias.persistence.entities.AlumnoDB;
import es.uva.inf.tutorias.persistence.entities.ProfesorDB;
import es.uva.inf.tutorias.persistence.entities.TutoriaDB;
import es.uva.inf.tutorias.persistence.entities.UsuarioDB;
import es.uva.inf.tutorias.persistence.repositories.TutoriaRepository;
import es.uva.inf.tutorias.persistence.repositories.UsuarioRepository;

@Service
public class TutoriaService {

	@Autowired
	private TutoriaRepository tutoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Tutoria getTutoria(String identificador, Long id) throws TutoriaNotFoundException {
		TutoriaDB tutoriaDB = null;
		UsuarioDB usuarioDB = usuarioRepository.findByIdentificador(identificador);

		if (usuarioDB != null && usuarioDB instanceof ProfesorDB) {
			tutoriaDB = tutoriaRepository.findBySolicitudTutoriaProfesorIdentificadorAndId(identificador, id);
		}

		if (usuarioDB != null && usuarioDB instanceof AlumnoDB) {
			tutoriaDB = tutoriaRepository.findBySolicitudTutoriaAlumnoIdentificadorAndId(identificador, id);
		}

		if (tutoriaDB == null) {
			throw new TutoriaNotFoundException();
		}

		return TutoriaConverter.convertToTutoria(tutoriaDB);
	}

	public List<Tutoria> getTutorias(String identificador, LocalDate fechaTutoria, LocalTime horaInicio,
			LocalTime horaFin, Boolean cancelada, Boolean grupal, String idProfesor, Integer codigoAsignatura)
			throws HoraFinMenorOIgualQueHoraInicioException {
		UsuarioDB usuarioDB = usuarioRepository.findByIdentificador(identificador);
		List<TutoriaDB> tutoriasDB = new ArrayList<>();

		if (usuarioDB != null && usuarioDB instanceof AlumnoDB) {
			tutoriasDB = tutoriaRepository.findTutoriasAlumnoBy(identificador,
					DateConverter.convertToDate(fechaTutoria), cancelada, grupal, idProfesor, codigoAsignatura);
		}

		if (usuarioDB != null && usuarioDB instanceof ProfesorDB) {
			if (horaInicio != null && horaFin != null && horaFin.compareTo(horaInicio) <= 0) {
				throw new HoraFinMenorOIgualQueHoraInicioException();
			}

			tutoriasDB = tutoriaRepository.findTutoriasProfesorBy(identificador,
					DateConverter.convertToDate(fechaTutoria), horaInicio, horaFin, cancelada, grupal,
					codigoAsignatura);
		}

		return tutoriasDB.stream().map(tutoriaDB -> TutoriaConverter.convertToTutoriaLight(tutoriaDB))
				.collect(Collectors.toList());
	}

	public List<Tutoria> getProximasTutorias(String identificador) {
		UsuarioDB usuarioDB = usuarioRepository.findByIdentificador(identificador);
		List<TutoriaDB> tutoriasDB = new ArrayList<>();
		Date hoy = new Date();

		if (usuarioDB != null && usuarioDB instanceof AlumnoDB) {
			tutoriasDB = tutoriaRepository.findTutoriasAlumnoFechaTutoriaMayorOIgualQue(identificador, hoy);
		}

		if (usuarioDB != null && usuarioDB instanceof ProfesorDB) {
			tutoriasDB = tutoriaRepository.findTutoriasProfesorFechaTutoriaMayorOIgualQue(identificador, hoy);
		}

		return tutoriasDB.stream().map(tutoriaDB -> TutoriaConverter.convertToTutoriaLight(tutoriaDB))
				.collect(Collectors.toList());
	}

	public List<Tutoria> getTutoriasHoy(String identificador) {
		UsuarioDB usuarioDB = usuarioRepository.findByIdentificador(identificador);
		List<TutoriaDB> tutoriasDB = new ArrayList<>();
		Date hoy = new Date();

		if (usuarioDB != null && usuarioDB instanceof AlumnoDB) {
			tutoriasDB = tutoriaRepository.findTutoriasAlumnoBy(identificador, hoy, false, null, null, null);
		}

		if (usuarioDB != null && usuarioDB instanceof ProfesorDB) {
			tutoriasDB = tutoriaRepository.findTutoriasProfesorBy(identificador, hoy, null, null, false, null, null);
		}

		return tutoriasDB.stream().map(tutoriaDB -> TutoriaConverter.convertToTutoriaLight(tutoriaDB))
				.collect(Collectors.toList());
	}

	public boolean existenTutoriasParaProfesorEnFechaIntervaloHoras(String identificador, LocalDate fechaTutoria,
			LocalTime horaInicio, LocalTime horaFin) {
		return tutoriaRepository.existsTutoriasParaProfesorEnFechaIntervaloHoras(identificador,
				DateConverter.convertToDate(fechaTutoria), horaInicio, horaFin);
	}

	public boolean existenTutoriasParaAlumnoEnFechaIntervaloHoras(String identificador, LocalDate fechaTutoria,
			LocalTime horaInicio, LocalTime horaFin) {
		return tutoriaRepository.existsTutoriasParaAlumnoEnFechaIntervaloHoras(identificador,
				DateConverter.convertToDate(fechaTutoria), horaInicio, horaFin);
	}

	@Transactional
	public Tutoria crearTutoria(Long solicitudId) {
		TutoriaDB nuevaTutoriaDB = new TutoriaDB();
		nuevaTutoriaDB.setSolicitudId(solicitudId);
		nuevaTutoriaDB.setCancelada(false);

		return TutoriaConverter.convertToTutoria(tutoriaRepository.save(nuevaTutoriaDB));
	}

	@Transactional
	public Tutoria cancelarTutoria(Long id, String motivoCancelacion) throws TutoriaNotFoundException {
		try {
			TutoriaDB tutoriaDB = tutoriaRepository.findById(id).get();
			tutoriaDB.setCancelada(true);
			tutoriaDB.setMotivoCancelacion(motivoCancelacion);

			return TutoriaConverter.convertToTutoria(tutoriaRepository.save(tutoriaDB));
		} catch (NoSuchElementException e) {
			throw new TutoriaNotFoundException();
		}
	}

}
