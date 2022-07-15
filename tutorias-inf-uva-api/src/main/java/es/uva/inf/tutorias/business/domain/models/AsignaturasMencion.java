package es.uva.inf.tutorias.business.domain.models;

import java.io.Serializable;

/**
 * The persistent class for the asignaturasmencion database table.
 * 
 */
public class AsignaturasMencion implements Serializable {
	private static final long serialVersionUID = 1L;

	private AsignaturasMencionPK id;
	private MencionTitulacion mencion;
	private CursoAcademico curso;

	public AsignaturasMencion() {
	}

	public AsignaturasMencionPK getId() {
		return this.id;
	}

	public void setId(AsignaturasMencionPK id) {
		this.id = id;
	}

	public MencionTitulacion getMencionTitulacion() {
		return mencion;
	}

	public void setMencionTitulacion(MencionTitulacion mencion) {
		this.mencion = mencion;
	}

	public CursoAcademico getCursoAcademico() {
		return curso;
	}

	public void setCursoAcademico(CursoAcademico curso) {
		this.curso = curso;
	}

}