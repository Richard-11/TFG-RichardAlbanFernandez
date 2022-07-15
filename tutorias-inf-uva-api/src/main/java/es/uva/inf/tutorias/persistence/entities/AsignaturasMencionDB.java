package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the asignaturasmencion database table.
 * 
 */
@Entity
@Table(name = "asignaturasmencion")
public class AsignaturasMencionDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsignaturasMencionPK id;

	@ManyToOne
	@JoinColumn(name = "mencionid", insertable = false, updatable = false)
	private MencionTitulacionDB mencion;

	@ManyToOne
	@JoinColumn(name = "cursoid")
	private CursoAcademicoDB curso;

	public AsignaturasMencionDB() {
	}

	public AsignaturasMencionPK getId() {
		return this.id;
	}

	public void setId(AsignaturasMencionPK id) {
		this.id = id;
	}

	public MencionTitulacionDB getMencionTitulacion() {
		return mencion;
	}

	public void setMencionTitulacion(MencionTitulacionDB mencion) {
		this.mencion = mencion;
	}

	public CursoAcademicoDB getCursoAcademico() {
		return curso;
	}

	public void setCursoAcademico(CursoAcademicoDB curso) {
		this.curso = curso;
	}

}