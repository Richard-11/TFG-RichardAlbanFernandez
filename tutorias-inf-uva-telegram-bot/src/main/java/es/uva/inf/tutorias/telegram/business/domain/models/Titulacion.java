package es.uva.inf.tutorias.telegram.business.domain.models;

import java.io.Serializable;
import java.util.List;

public class Titulacion implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	private String nombre;
	private List<CursoAcademico> cursosAcademicos;

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

	public List<CursoAcademico> getCursosAcademicos() {
		return cursosAcademicos;
	}

	public void setCursosAcademicos(List<CursoAcademico> cursosAcademicos) {
		this.cursosAcademicos = cursosAcademicos;
	}

}