package es.uva.inf.tutorias.business.domain.exceptions;

public class HoraFinMenorOIgualQueHoraInicioException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public HoraFinMenorOIgualQueHoraInicioException() {
		super("La hora de fin no puede ser anterior o igual que la hora de inicio");
	}

}
