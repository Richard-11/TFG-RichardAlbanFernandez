package es.uva.inf.tutorias.business.domain.converters;

import java.util.stream.Collectors;

import es.uva.inf.tutorias.business.domain.models.FranjaTutorias;
import es.uva.inf.tutorias.business.domain.models.HorarioTutorias;
import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.business.domain.models.TipoHorario;
import es.uva.inf.tutorias.persistence.entities.FranjaTutoriasDB;
import es.uva.inf.tutorias.persistence.entities.HorarioTutoriasDB;
import es.uva.inf.tutorias.persistence.entities.ProfesorDB;
import es.uva.inf.tutorias.persistence.entities.TipoHorarioDB;

public class HorarioTutoriasConverter {

	public static HorarioTutorias convertToHorarioTutorias(HorarioTutoriasDB horarioTutoriasDB) {
		HorarioTutorias horarioTutorias = new HorarioTutorias();
		horarioTutorias.setId(horarioTutoriasDB.getId());
		
		ProfesorDB profesorDB = horarioTutoriasDB.getProfesor();
		if (profesorDB != null) {
			horarioTutorias.setProfesor(ProfesorConverter.convertToProfesorLight(profesorDB));
		}
		
		TipoHorarioDB tipoHorarioDB = horarioTutoriasDB.getTipoHorario();
		if (tipoHorarioDB != null) {
			horarioTutorias.setTipoHorario(TipoHorarioConverter.convertToTipoHorario(horarioTutoriasDB.getTipoHorario()));
		}
		
		if (horarioTutoriasDB.getFranjasTutorias() != null) {
			horarioTutorias.setFranjasTutorias(horarioTutoriasDB.getFranjasTutorias().stream()
					.map(franjaDB -> FranjaTutoriasConverter.convertToFranjaTutorias(franjaDB))
					.collect(Collectors.toList()));
		}
		
		return horarioTutorias;
	}

	public static HorarioTutoriasDB convertToHorarioTutoriasDB(HorarioTutorias horarioTutorias) {
		HorarioTutoriasDB horarioTutoriasDB = new HorarioTutoriasDB();
		horarioTutoriasDB.setId(horarioTutorias.getId());
		
		Profesor profesor = horarioTutorias.getProfesor();
		if (profesor != null) {
			horarioTutoriasDB.setProfesor(ProfesorConverter.convertToProfesorDB(profesor));
		}
		
		TipoHorario tipoHorario = horarioTutorias.getTipoHorario();
		if (tipoHorario != null) {
			horarioTutoriasDB.setTipoHorario(TipoHorarioConverter.convertToTipoHorarioDB(tipoHorario));
		}
		
		if (horarioTutorias.getFranjasTutorias() != null) {
			for (FranjaTutorias franja : horarioTutorias.getFranjasTutorias()) {
				FranjaTutoriasDB franjaDB = FranjaTutoriasConverter.convertToFranjaTutoriasDB(franja);
				horarioTutoriasDB.addFranjaTutorias(franjaDB);
			}
		}
		
		return horarioTutoriasDB;
	}

}
