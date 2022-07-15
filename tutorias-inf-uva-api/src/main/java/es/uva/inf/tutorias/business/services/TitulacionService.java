package es.uva.inf.tutorias.business.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.business.domain.converters.TitulacionConverter;
import es.uva.inf.tutorias.business.domain.exceptions.TitulacionNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Titulacion;
import es.uva.inf.tutorias.persistence.entities.TitulacionDB;
import es.uva.inf.tutorias.persistence.repositories.TitulacionRepository;

@Service
public class TitulacionService {

	@Autowired
	private TitulacionRepository titulacionRepository;

	public List<Titulacion> getTitulaciones() {
		return titulacionRepository.findAll().stream()
				.map(titulacionDB -> TitulacionConverter.convertToTitulacion(titulacionDB))
				.collect(Collectors.toList());
	}

	public Titulacion getTitulacion(Integer codigoTitulacion) throws TitulacionNotFoundException {
		try {
			return TitulacionConverter.convertToTitulacion(titulacionRepository.findById(codigoTitulacion).get());
		} catch (NoSuchElementException e) {
			throw new TitulacionNotFoundException(codigoTitulacion);
		}
	}

	public List<Titulacion> getTitulaciones(String identificador) throws TitulacionNotFoundException {
		List<TitulacionDB> titulacionesDB = titulacionRepository.findByUsuariosIdentificador(identificador);
		
		if (titulacionesDB == null) {
			throw new TitulacionNotFoundException(identificador);
		}
		
		return titulacionesDB.stream().map(titulacionDB -> TitulacionConverter.convertToTitulacion(titulacionDB)).collect(Collectors.toList());
	}
}
