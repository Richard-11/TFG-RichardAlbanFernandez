package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nombre;
	private String acronimo;
	private Set<CursoAcademico> cursos;
	private List<AsignaturasMencion> asignaturasMencion;
	private Set<Usuario> usuarios;

	public Asignatura() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAcronimo() {
		return this.acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public Set<CursoAcademico> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoAcademico> cursos) {
		this.cursos = cursos;
	}

	public List<AsignaturasMencion> getAsignaturasMencion() {
		return asignaturasMencion;
	}

	public void setAsignaturasMencion(List<AsignaturasMencion> asignaturasMencion) {
		this.asignaturasMencion = asignaturasMencion;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}