package es.uva.inf.tutorias.business.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.business.domain.converters.AsignaturaConverter;
import es.uva.inf.tutorias.business.domain.exceptions.AsignaturaNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Asignatura;
import es.uva.inf.tutorias.persistence.repositories.AsignaturaRepository;

@Service
public class AsignaturaService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;

	public List<Asignatura> getAsignaturas(Integer codigoTitulacion, Integer cursoId, Integer mencionId,
			String nombre) {
		return asignaturaRepository.findBy(codigoTitulacion, cursoId, mencionId, nombre).stream()
				.map(asignaturaDB -> AsignaturaConverter.convertToAsignatura(asignaturaDB))
				.collect(Collectors.toList());
	}

	public List<Asignatura> getAsignaturasUsuario(String identificador, String nombre) {
		return asignaturaRepository.findByIdentificadorUsuarioAndNombreAsignatura(identificador, nombre).stream()
				.map(asignaturaDB -> AsignaturaConverter.convertToAsignatura(asignaturaDB))
				.collect(Collectors.toList());
	}

	public Asignatura getAsignatura(Integer codigo) throws AsignaturaNotFoundException {
		try {
			return AsignaturaConverter.convertToAsignaturaLight(asignaturaRepository.findById(codigo).get());
		} catch (NoSuchElementException e) {
			throw new AsignaturaNotFoundException();
		}
	}

}
