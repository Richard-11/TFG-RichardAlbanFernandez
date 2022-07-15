package es.uva.inf.tutorias.telegram.business.domain.models;

import java.io.Serializable;
import java.util.Set;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private String identificador;
	private String nombre;
	private String apellidos;
	private String nif;
	private String email;
	private String password;
	private Set<Titulacion> titulaciones;
	private Set<Asignatura> asignaturas;

	public Usuario() {
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Titulacion> getTitulaciones() {
		return this.titulaciones;
	}

	public void setTitulaciones(Set<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	public Set<Asignatura> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(Set<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

}