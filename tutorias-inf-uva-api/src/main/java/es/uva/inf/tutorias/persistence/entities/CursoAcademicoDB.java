package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cursoacademico database table.
 * 
 */
@Entity
@Table(name = "cursoacademico")
public class CursoAcademicoDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String curso;
	
	@Column(name = "cursonumerico")
	private short cursoNumerico;

	@ManyToOne
	@JoinColumn(name = "codigo", nullable = false)
	private TitulacionDB titulacion;

	@ManyToMany
	@JoinTable(name = "mencionescurso", joinColumns = { @JoinColumn(name = "cursoid") }, inverseJoinColumns = {
			@JoinColumn(name = "mencionid") })
	private List<MencionTitulacionDB> menciones;

	public CursoAcademicoDB() {
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

	public TitulacionDB getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(TitulacionDB titulacion) {
		this.titulacion = titulacion;
	}

	public List<MencionTitulacionDB> getMenciones() {
		return menciones;
	}

	public void setMenciones(List<MencionTitulacionDB> menciones) {
		this.menciones = menciones;
	}

}