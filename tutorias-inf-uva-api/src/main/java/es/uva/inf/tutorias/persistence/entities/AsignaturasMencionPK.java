package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the asignaturasmencion database table.
 * 
 */
@Embeddable
public class AsignaturasMencionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "mencionid", insertable=false, updatable=false)
	private Integer mencionId;

	@Column(insertable=false, updatable=false)
	private Integer codigo;

	public AsignaturasMencionPK() {
	}
	public Integer getMencionId() {
		return this.mencionId;
	}
	public void setMencionId(Integer mencionid) {
		this.mencionId = mencionid;
	}
	public Integer getCodigo() {
		return this.codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsignaturasMencionPK)) {
			return false;
		}
		AsignaturasMencionPK castOther = (AsignaturasMencionPK)other;
		return 
			this.mencionId.equals(castOther.mencionId)
			&& this.codigo.equals(castOther.codigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mencionId.hashCode();
		hash = hash * prime + this.codigo.hashCode();
		
		return hash;
	}
}