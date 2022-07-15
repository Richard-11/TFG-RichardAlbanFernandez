package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.models.CursoAcademico;
import es.uva.inf.tutorias.business.domain.models.Titulacion;
import es.uva.inf.tutorias.persistence.entities.CursoAcademicoDB;
import es.uva.inf.tutorias.persistence.entities.TitulacionDB;

public class CursoAcademicoConverter {

	public static CursoAcademico convertToCursoAcademicoTitulacion(CursoAcademicoDB cursoDB) {
		CursoAcademico curso = new CursoAcademico();
		curso.setId(cursoDB.getId());
		curso.setCurso(cursoDB.getCurso());
		curso.setCursoNumerico(cursoDB.getCursoNumerico());
		
		TitulacionDB titulacionDB = cursoDB.getTitulacion();
		if (titulacionDB != null) {
			Titulacion titulacion = new Titulacion();
			titulacion.setCodigo(titulacionDB.getCodigo());
			titulacion.setNombre(titulacionDB.getNombre());
			titulacion.setPlan(titulacionDB.getPlan());
			
			curso.setTitulacion(titulacion);
		}

		return curso;
	}

	public static CursoAcademicoDB convertToCursoAcademicoTitulacionDB(CursoAcademico curso) {
		CursoAcademicoDB cursoDB = new CursoAcademicoDB();
		cursoDB.setId(curso.getId());
		cursoDB.setCurso(curso.getCurso());
		cursoDB.setCursoNumerico(curso.getCursoNumerico());
		
		Titulacion titulacion = curso.getTitulacion();
		if (titulacion != null) {
			TitulacionDB titulacionDB = new TitulacionDB();
			titulacionDB.setCodigo(titulacion.getCodigo());
			titulacionDB.setNombre(titulacion.getNombre());
			titulacionDB.setPlan(titulacion.getPlan());
			
			cursoDB.setTitulacion(titulacionDB);
		}

		return cursoDB;
	}
	
}
