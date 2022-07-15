package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.models.MencionTitulacion;
import es.uva.inf.tutorias.persistence.entities.MencionTitulacionDB;

public class MencionTitulacionConverter {

	public static MencionTitulacion convertToMencionTitulacion(MencionTitulacionDB mencionDB) {
		MencionTitulacion mencion = new MencionTitulacion();
		mencion.setId(mencionDB.getId());
		mencion.setNombre(mencionDB.getNombre());
		mencion.setAcronimo(mencionDB.getAcronimo());
		
		return mencion;
	}

	public static MencionTitulacionDB convertToMencionTitulacionDB(MencionTitulacion mencion) {
		MencionTitulacionDB mencionDB = new MencionTitulacionDB();
		mencionDB.setId(mencion.getId());
		mencionDB.setNombre(mencion.getNombre());
		mencionDB.setAcronimo(mencion.getAcronimo());
		
		return mencionDB;
	}
}
