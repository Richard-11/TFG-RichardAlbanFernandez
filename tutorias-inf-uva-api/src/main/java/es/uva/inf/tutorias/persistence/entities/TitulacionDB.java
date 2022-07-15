package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the titulacion database table.
 * 
 */
@Entity
@Table(name = "titulacion")
public class TitulacionDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer codigo;

	private String nombre;

	private Short plan;

	@ManyToOne
	@JoinColumn(name = "niveltitulacionid")
	private NivelTitulacionDB nivelTitulacion;

	@OneToMany(mappedBy = "titulacion")
	private List<CursoAcademicoDB> cursosAcademicos;

	@ManyToMany(mappedBy = "titulaciones")
	private Set<UsuarioDB> usuarios;

	public TitulacionDB() {
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

	public Short getPlan() {
		return this.plan;
	}

	public void setPlan(Short plan) {
		this.plan = plan;
	}

	public NivelTitulacionDB getNivelTitulacion() {
		return this.nivelTitulacion;
	}

	public void setNivelTitulacion(NivelTitulacionDB nivelTitulacion) {
		this.nivelTitulacion = nivelTitulacion;
	}

	public List<CursoAcademicoDB> getCursosAcademicos() {
		return cursosAcademicos;
	}

	public void setCursosAcademicos(List<CursoAcademicoDB> cursosAcademicos) {
		this.cursosAcademicos = cursosAcademicos;
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
		if (!(obj instanceof TitulacionDB))
			return false;
		return codigo != null && codigo.equals(((TitulacionDB) obj).getCodigo());
	}

}