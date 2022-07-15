package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.enums.Dia;
import es.uva.inf.tutorias.persistence.entities.DiaDB;

public class DiaConverter {

	public static Dia convertToDia(DiaDB diaDB) {
		return Dia.getById(diaDB.getId());
	}

	public static DiaDB convertToDiaDB(Dia dia) {
		DiaDB diaDB = new DiaDB();
		diaDB.setId(dia.id);
		diaDB.setNombre(dia.nombre);
		
		return diaDB;
	}

}
