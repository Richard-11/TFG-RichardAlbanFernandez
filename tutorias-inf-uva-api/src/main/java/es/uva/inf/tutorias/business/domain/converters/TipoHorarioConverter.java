package es.uva.inf.tutorias.business.domain.converters;

import es.uva.inf.tutorias.business.domain.enums.TipoHorarioEnum;
import es.uva.inf.tutorias.business.domain.models.TipoHorario;
import es.uva.inf.tutorias.persistence.entities.TipoHorarioDB;

public class TipoHorarioConverter {

	public static TipoHorario convertToTipoHorario(TipoHorarioDB tipoHorarioDB) {
		TipoHorario tipoHorario = new TipoHorario();
		tipoHorario.setId(tipoHorarioDB.getId());
		tipoHorario.setTipo(tipoHorarioDB.getTipo());
		tipoHorario.setFechaInicio(DateConverter.convertToLocalDate(tipoHorarioDB.getFechaInicio()));
		tipoHorario.setFechaFin(DateConverter.convertToLocalDate(tipoHorarioDB.getFechaFin()));
		
		return tipoHorario;
	}

	public static TipoHorarioDB convertToTipoHorarioDB(TipoHorario tipoHorario) {
		TipoHorarioDB tipoHorarioDB = new TipoHorarioDB();
		tipoHorarioDB.setId(tipoHorario.getId());
		tipoHorarioDB.setTipo(TipoHorarioEnum.getById(tipoHorario.getId()).tipo);
		tipoHorarioDB.setFechaInicio(DateConverter.convertToDate(tipoHorario.getFechaInicio()));
		tipoHorarioDB.setFechaFin(DateConverter.convertToDate(tipoHorario.getFechaFin()));

		return tipoHorarioDB;
	}

}
