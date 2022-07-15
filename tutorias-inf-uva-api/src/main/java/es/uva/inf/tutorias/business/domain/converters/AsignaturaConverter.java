package es.uva.inf.tutorias.business.domain.converters;

import java.util.stream.Collectors;

import es.uva.inf.tutorias.business.domain.models.Asignatura;
import es.uva.inf.tutorias.persistence.entities.AsignaturaDB;

public class AsignaturaConverter {

	public static Asignatura convertToAsignatura(AsignaturaDB asignaturaDB) {
		Asignatura asignatura = new Asignatura();
		asignatura.setCodigo(asignaturaDB.getCodigo());
		asignatura.setNombre(asignaturaDB.getNombre());
		asignatura.setAcronimo(asignaturaDB.getAcronimo());

		if (asignaturaDB.getCursos() != null) {
			asignatura.setCursos(asignaturaDB.getCursos().stream()
					.map(cursoDB -> CursoAcademicoConverter.convertToCursoAcademicoTitulacion(cursoDB))
					.collect(Collectors.toSet()));
		}

		return asignatura;
	}

	public static AsignaturaDB convertToAsignaturaDB(Asignatura asignatura) {
		AsignaturaDB asignaturaDB = new AsignaturaDB();
		asignaturaDB.setCodigo(asignatura.getCodigo());
		asignaturaDB.setNombre(asignatura.getNombre());
		asignaturaDB.setAcronimo(asignatura.getAcronimo());

		if (asignatura.getCursos() != null) {
			asignaturaDB.setCursos(asignatura.getCursos().stream()
					.map(curso -> CursoAcademicoConverter.convertToCursoAcademicoTitulacionDB(curso))
					.collect(Collectors.toSet()));
		}

		return asignaturaDB;
	}

	public static Asignatura convertToAsignaturaLight(AsignaturaDB asignaturaDB) {
		Asignatura asignatura = new Asignatura();
		asignatura.setCodigo(asignaturaDB.getCodigo());
		asignatura.setNombre(asignaturaDB.getNombre());
		asignatura.setAcronimo(asignaturaDB.getAcronimo());

		return asignatura;
	}

}
