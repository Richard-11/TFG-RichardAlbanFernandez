package es.uva.inf.tutorias.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@Table(name = "alumno")
@PrimaryKeyJoinColumn(referencedColumnName = "identificador")
public class AlumnoDB extends UsuarioDB {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nia")
	private Integer nia;

	public AlumnoDB() {
	}

	public Integer getNia() {
		return this.nia;
	}

	public void setNia(Integer nia) {
		this.nia = nia;
	}
	
}