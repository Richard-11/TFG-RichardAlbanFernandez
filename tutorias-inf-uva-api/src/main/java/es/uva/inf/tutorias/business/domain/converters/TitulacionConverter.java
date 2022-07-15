package es.uva.inf.tutorias.business.domain.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.uva.inf.tutorias.business.domain.models.CursoAcademico;
import es.uva.inf.tutorias.business.domain.models.Titulacion;
import es.uva.inf.tutorias.persistence.entities.CursoAcademicoDB;
import es.uva.inf.tutorias.persistence.entities.TitulacionDB;

public class TitulacionConverter {

	public static Titulacion convertToTitulacion(TitulacionDB titulacionDB) {
		Titulacion titulacion = new Titulacion();
		titulacion.setCodigo(titulacionDB.getCodigo());
		titulacion.setNombre(titulacionDB.getNombre());
		titulacion.setPlan(titulacionDB.getPlan());
		titulacion.setNivelTitulacion(NivelTitulacionConverter.convertToNivelTitulacion(titulacionDB.getNivelTitulacion()));
		
		List<CursoAcademicoDB> cursosDB = titulacionDB.getCursosAcademicos();
		if (cursosDB != null) {
			List<CursoAcademico> cursos = new ArrayList<>();
			
			for (CursoAcademicoDB cursoDB : cursosDB) {
				CursoAcademico curso = new CursoAcademico();
				curso.setId(cursoDB.getId());
				curso.setCurso(cursoDB.getCurso());
				curso.setCursoNumerico(cursoDB.getCursoNumerico());
				
				curso.setMenciones(cursoDB.getMenciones().stream()
						.map(mencionDB -> MencionTitulacionConverter.convertToMencionTitulacion(mencionDB))
						.collect(Collectors.toList()));
				
				cursos.add(curso);
			}
			
			titulacion.setCursosAcademicos(cursos);
		}
		
		return titulacion;
	}

}
