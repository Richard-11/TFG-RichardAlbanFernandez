package es.uva.inf.tutorias.business.domain.converters;

import java.util.stream.Collectors;

import es.uva.inf.tutorias.business.domain.models.Alumno;
import es.uva.inf.tutorias.business.domain.models.Titulacion;
import es.uva.inf.tutorias.persistence.entities.AlumnoDB;

public class AlumnoConverter {

	public static Alumno convertToAlumno(AlumnoDB alumnoDB) {
		Alumno alumno = new Alumno();
		alumno.setNombre(alumnoDB.getNombre());
		alumno.setApellidos(alumnoDB.getApellidos());
		alumno.setNif(alumnoDB.getNif());
		alumno.setEmail(alumnoDB.getEmail());
		alumno.setIdentificador(alumnoDB.getIdentificador());
		alumno.setNia(alumnoDB.getNia());

		if (alumnoDB.getTitulaciones() != null) {
			alumno.setTitulaciones(alumnoDB.getTitulaciones().stream().map(titulacionDB -> {
				Titulacion titulacion = new Titulacion();
				titulacion.setCodigo(titulacionDB.getCodigo());
				titulacion.setNombre(titulacionDB.getNombre());
				titulacion.setPlan(titulacionDB.getPlan());
				titulacion.setNivelTitulacion(
						NivelTitulacionConverter.convertToNivelTitulacion(titulacionDB.getNivelTitulacion()));

				return titulacion;
			}).collect(Collectors.toSet()));
		}

		return alumno;
	}

	public static AlumnoDB convertToAlumnoDB(Alumno alumno) {
		AlumnoDB alumnoDB = new AlumnoDB();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellidos(alumno.getApellidos());
		alumnoDB.setNif(alumno.getNif());
		alumnoDB.setEmail(alumno.getEmail());
		alumnoDB.setIdentificador(alumno.getIdentificador());
		alumnoDB.setNia(alumno.getNia());

		return alumnoDB;
	}

	public static Alumno convertToAlumnoLight(AlumnoDB alumnoDB) {
		Alumno alumno = new Alumno();
		alumno.setNombre(alumnoDB.getNombre());
		alumno.setApellidos(alumnoDB.getApellidos());
		alumno.setNif(alumnoDB.getNif());
		alumno.setEmail(alumnoDB.getEmail());
		alumno.setIdentificador(alumnoDB.getIdentificador());
		alumno.setNia(alumnoDB.getNia());

		return alumno;
	}
}
