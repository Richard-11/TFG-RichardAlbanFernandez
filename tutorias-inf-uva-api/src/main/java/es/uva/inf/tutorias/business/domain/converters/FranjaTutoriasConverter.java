package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.enums.Dia;
import es.uva.inf.tutorias.business.domain.models.FranjaTutorias;
import es.uva.inf.tutorias.persistence.entities.DiaDB;
import es.uva.inf.tutorias.persistence.entities.FranjaTutoriasDB;

public class FranjaTutoriasConverter {

	public static FranjaTutorias convertToFranjaTutorias(FranjaTutoriasDB franjaTutoriasDB) {
		FranjaTutorias franjaTutorias = new FranjaTutorias();
		franjaTutorias.setId(franjaTutoriasDB.getId());
		franjaTutorias.setHoraInicio(franjaTutoriasDB.getHoraInicio());
		franjaTutorias.setHoraFin(franjaTutoriasDB.getHoraFin());
		franjaTutorias.setCentro(franjaTutoriasDB.getCentro());
		franjaTutorias.setDespacho(franjaTutoriasDB.getDespacho());

		DiaDB diaDB = franjaTutoriasDB.getDia();
		if (diaDB != null) {
			franjaTutorias.setDia(DiaConverter.convertToDia(diaDB));
		}

		return franjaTutorias;
	}

	public static FranjaTutoriasDB convertToFranjaTutoriasDB(FranjaTutorias franjaTutorias) {
		FranjaTutoriasDB franjaTutoriasDB = new FranjaTutoriasDB();
		franjaTutoriasDB.setId(franjaTutorias.getId());
		franjaTutoriasDB.setHoraInicio(franjaTutorias.getHoraInicio());
		franjaTutoriasDB.setHoraFin(franjaTutorias.getHoraFin());
		franjaTutoriasDB.setCentro(franjaTutorias.getCentro());
		franjaTutoriasDB.setDespacho(franjaTutorias.getDespacho());

		Dia dia = franjaTutorias.getDia();
		if (dia != null) {
			franjaTutoriasDB.setDia(DiaConverter.convertToDiaDB(dia));
		}

		return franjaTutoriasDB;
	}

}
