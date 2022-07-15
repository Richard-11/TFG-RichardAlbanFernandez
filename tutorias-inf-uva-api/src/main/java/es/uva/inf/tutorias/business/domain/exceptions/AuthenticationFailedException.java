package es.uva.inf.tutorias.business.domain.exceptions;

public class AuthenticationFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public AuthenticationFailedException() {
		super("Usuario o contraseña incorrectos");
	}
}
