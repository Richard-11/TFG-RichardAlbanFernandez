package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String identificador;

	private String nombre;

	private String apellidos;

	private String nif;

	private String email;

	private String password;

	@ManyToMany
	@JoinTable(name = "titulacionesusuario", joinColumns = @JoinColumn(name = "identificador"), inverseJoinColumns = @JoinColumn(name = "codigo"))
	private Set<TitulacionDB> titulaciones;

	@ManyToMany
	@JoinTable(name = "asignaturasusuario", joinColumns = @JoinColumn(name = "identificador"), inverseJoinColumns = @JoinColumn(name = "codigo"))
	private Set<AsignaturaDB> asignaturas;

	public UsuarioDB() {
		titulaciones = new HashSet<>();
		asignaturas = new HashSet<>();
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

	public Set<TitulacionDB> getTitulaciones() {
		return this.titulaciones;
	}

	public void setTitulaciones(Set<TitulacionDB> titulaciones) {
		this.titulaciones = titulaciones;
	}

	public Set<AsignaturaDB> getAsignaturas() {
		return this.asignaturas;
	}

	public void setAsignaturas(Set<AsignaturaDB> asignaturas) {
		this.asignaturas = asignaturas;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UsuarioDB))
			return false;
		return identificador != null && identificador.equals(((UsuarioDB) obj).getIdentificador());
	}

}