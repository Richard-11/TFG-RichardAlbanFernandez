package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.models.NivelTitulacion;
import es.uva.inf.tutorias.persistence.entities.NivelTitulacionDB;

public class NivelTitulacionConverter {
	
	public static NivelTitulacion convertToNivelTitulacion(NivelTitulacionDB nivelDB) {
		NivelTitulacion nivel = new NivelTitulacion();
		nivel.setId(nivelDB.getId());
		nivel.setNivel(nivelDB.getNivel());
		
		return nivel;
	}

}
