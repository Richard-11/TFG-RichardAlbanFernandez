package es.uva.inf.tutorias.business.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.business.domain.converters.ProfesorConverter;
import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.persistence.repositories.ProfesorRepository;

@Service
public class ProfesorService {

	@Autowired
	private ProfesorRepository profesorRepository;

	public Profesor getProfesor(String identificador) {
		return ProfesorConverter.convertToProfesor(profesorRepository.findByIdentificador(identificador));
	}

	public List<Profesor> getProfesores(String nombre) {
		return profesorRepository.findBy(nombre).stream()
				.map(profesorDB -> ProfesorConverter.convertToProfesorLight(profesorDB)).collect(Collectors.toList());

	}

	public List<Profesor> getProfesoresAsignatura(Integer codigo) {
		return profesorRepository.findByAsignaturasCodigo(codigo).stream()
				.map(profesorDB -> ProfesorConverter.convertToProfesorLight(profesorDB)).collect(Collectors.toList());
	}

	public List<Profesor> getProfesoresTitulacion(Integer codigo) {
		return profesorRepository.findByTitulacionesCodigo(codigo).stream()
				.map(profesorDB -> ProfesorConverter.convertToProfesorLight(profesorDB)).collect(Collectors.toList());
	}

}
