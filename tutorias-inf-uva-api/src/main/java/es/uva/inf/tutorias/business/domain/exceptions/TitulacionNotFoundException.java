package es.uva.inf.tutorias.business.domain.exceptions;

public class TitulacionNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TitulacionNotFoundException(Integer codigoTitulacion) {
		super("No se encontró ninguna titulación con el código " + codigoTitulacion);
	}

	public TitulacionNotFoundException(String identificadorUsuario) {
		super(String.format("No se encontraron titulaciones para el usuario con identificador '%s'", identificadorUsuario));
	}
}
