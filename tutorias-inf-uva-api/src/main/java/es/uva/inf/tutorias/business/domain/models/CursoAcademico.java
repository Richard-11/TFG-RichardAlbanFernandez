package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the cursoacademico database table.
 * 
 */
public class CursoAcademico implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String curso;
	private short cursoNumerico;
	private Titulacion titulacion;
	private List<MencionTitulacion> menciones;

	public CursoAcademico() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public short getCursoNumerico() {
		return cursoNumerico;
	}

	public void setCursoNumerico(short cursoNumerico) {
		this.cursoNumerico = cursoNumerico;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<MencionTitulacion> getMenciones() {
		return menciones;
	}

	public void setMenciones(List<MencionTitulacion> menciones) {
		this.menciones = menciones;
	}

}