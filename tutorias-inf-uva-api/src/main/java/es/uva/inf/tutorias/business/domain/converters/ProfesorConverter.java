package es.uva.inf.tutorias.business.domain.converters;

import java.util.stream.Collectors;

import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.persistence.entities.ProfesorDB;

public class ProfesorConverter {

	public static Profesor convertToProfesor(ProfesorDB profesorDB) {
		Profesor profesor = new Profesor();
		profesor.setIdentificador(profesorDB.getIdentificador());
		profesor.setNombre(profesorDB.getNombre());
		profesor.setApellidos(profesorDB.getApellidos());
		profesor.setNif(profesorDB.getNif());
		profesor.setEmail(profesorDB.getEmail());
		profesor.setPassword(profesorDB.getPassword());
		profesor.setCentroHabitual(profesorDB.getCentroHabitual());
		profesor.setDespacho(profesorDB.getDespacho());
		profesor.setTelefono(profesorDB.getTelefono());
		
		if (profesorDB.getHorariosTutorias() != null) {
			profesor.setHorariosTutorias(profesorDB.getHorariosTutorias().stream()
					.map(horarioDB -> HorarioTutoriasConverter.convertToHorarioTutorias(horarioDB))
					.collect(Collectors.toList()));
		}
		
		return profesor;
	}

	public static ProfesorDB convertToProfesorDB(Profesor profesor) {
		ProfesorDB profesorDB = new ProfesorDB();
		profesorDB.setIdentificador(profesor.getIdentificador());
		profesorDB.setNombre(profesor.getNombre());
		profesorDB.setApellidos(profesor.getApellidos());
		profesorDB.setNif(profesor.getNif());
		profesorDB.setEmail(profesor.getEmail());
		profesorDB.setPassword(profesor.getPassword());
		profesorDB.setCentroHabitual(profesor.getCentroHabitual());
		profesorDB.setDespacho(profesor.getDespacho());
		profesorDB.setTelefono(profesor.getTelefono());
		
		if (profesor.getHorariosTutorias() != null) {
			profesorDB.setHorariosTutorias(profesor.getHorariosTutorias().stream()
					.map(horario -> HorarioTutoriasConverter.convertToHorarioTutoriasDB(horario))
					.collect(Collectors.toList()));
		}
		
		return profesorDB;
	}

	public static Profesor convertToProfesorLight(ProfesorDB profesorDB) {
		Profesor profesor = new Profesor();
		profesor.setIdentificador(profesorDB.getIdentificador());
		profesor.setNombre(profesorDB.getNombre());
		profesor.setApellidos(profesorDB.getApellidos());
		profesor.setNif(profesorDB.getNif());
		profesor.setEmail(profesorDB.getEmail());
		profesor.setPassword(profesorDB.getPassword());
		profesor.setCentroHabitual(profesorDB.getCentroHabitual());
		profesor.setDespacho(profesorDB.getDespacho());
		profesor.setTelefono(profesorDB.getTelefono());

		return profesor;
	}
}
