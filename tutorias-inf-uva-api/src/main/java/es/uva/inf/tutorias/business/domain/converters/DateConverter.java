package es.uva.inf.tutorias.business.domain.converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter {

	public static Date convertToDate(LocalDateTime dateToConvert) {
		return dateToConvert != null ? java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant()) : null;
	}

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return dateToConvert != null ? dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() : null;
	}

	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert != null ? new java.sql.Date(dateToConvert.getTime()).toLocalDate() : null;
	}

	public static Date convertToDate(LocalDate dateToConvert) {
		return dateToConvert != null ? java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) : null;
	}
	
}
