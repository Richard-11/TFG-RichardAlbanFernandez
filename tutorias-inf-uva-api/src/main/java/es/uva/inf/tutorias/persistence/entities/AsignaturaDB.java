package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the asignatura database table.
 * 
 */
@Entity
@Table(name = "asignatura")
public class AsignaturaDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;

	private String nombre;

	private String acronimo;

	@ManyToMany
	@JoinTable(name = "asignaturascurso", joinColumns = @JoinColumn(name = "codigo"), inverseJoinColumns = @JoinColumn(name = "cursoid"))
	private Set<CursoAcademicoDB> cursos;

	@OneToMany
	@JoinColumn(name = "codigo")
	private List<AsignaturasMencionDB> asignaturasMencion;

	@ManyToMany(mappedBy = "asignaturas")
	private Set<UsuarioDB> usuarios;

	public AsignaturaDB() {
		usuarios = new HashSet<>();
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

	public Set<CursoAcademicoDB> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoAcademicoDB> cursos) {
		this.cursos = cursos;
	}

	public List<AsignaturasMencionDB> getAsignaturasMencion() {
		return asignaturasMencion;
	}

	public void setAsignaturasMencion(List<AsignaturasMencionDB> asignaturasMencion) {
		this.asignaturasMencion = asignaturasMencion;
	}

	public Set<UsuarioDB> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<UsuarioDB> usuarios) {
		this.usuarios = usuarios;
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AsignaturaDB))
			return false;
		return codigo != null && codigo.equals(((AsignaturaDB) obj).getCodigo());
	}
}