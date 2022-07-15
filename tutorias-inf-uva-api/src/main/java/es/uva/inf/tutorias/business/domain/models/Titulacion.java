package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Titulacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nombre;
	private Short plan;
	private NivelTitulacion nivelTitulacion;
	private List<CursoAcademico> cursosAcademicos;
	private Set<Usuario> usuarios; 

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

	public NivelTitulacion getNivelTitulacion() {
		return this.nivelTitulacion;
	}

	public void setNivelTitulacion(NivelTitulacion nivelTitulacion) {
		this.nivelTitulacion = nivelTitulacion;
	}

	public List<CursoAcademico> getCursosAcademicos() {
		return cursosAcademicos;
	}

	public void setCursosAcademicos(List<CursoAcademico> cursosAcademicos) {
		this.cursosAcademicos = cursosAcademicos;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}