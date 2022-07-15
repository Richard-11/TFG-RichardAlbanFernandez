package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.models.Tutoria;
import es.uva.inf.tutorias.persistence.entities.TutoriaDB;

public class TutoriaConverter {

	public static Tutoria convertToTutoria(TutoriaDB tutoriaDB) {
		Tutoria tutoria = new Tutoria();
		tutoria.setId(tutoriaDB.getId());
		tutoria.setCancelada(tutoriaDB.getCancelada());
		tutoria.setMotivoCancelacion(tutoriaDB.getMotivoCancelacion());

		if (tutoriaDB.getSolicitudTutoria() != null) {
			tutoria.setSolicitudTutoria(
					SolicitudTutoriaConverter.convertToSolicitudTutoria(tutoriaDB.getSolicitudTutoria()));
		}

		return tutoria;
	}

	public static Tutoria convertToTutoriaLight(TutoriaDB tutoriaDB) {
		Tutoria tutoria = new Tutoria();
		tutoria.setId(tutoriaDB.getId());
		tutoria.setCancelada(tutoriaDB.getCancelada());
		tutoria.setMotivoCancelacion(tutoriaDB.getMotivoCancelacion());
		tutoria.setSolicitudTutoria(
				SolicitudTutoriaConverter.convertToSolicitudTutoriaLight(tutoriaDB.getSolicitudTutoria()));

		return tutoria;
	}

}
