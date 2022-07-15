package es.uva.inf.tutorias.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the profesor database table.
 * 
 */
@Entity
@Table(name = "profesor")
@PrimaryKeyJoinColumn(referencedColumnName = "identificador")
public class ProfesorDB extends UsuarioDB {
	private static final long serialVersionUID = 1L;

	@Column(name = "centrohabitual")
	private String centroHabitual;
	
	private String despacho;

	private String telefono;

	@OneToMany
	@JoinColumn(name = "idprofesor")
	private List<HorarioTutoriasDB> horariosTutorias;

	public ProfesorDB() {
	}

	public String getCentroHabitual() {
		return centroHabitual;
	}

	public void setCentroHabitual(String centroHabitual) {
		this.centroHabitual = centroHabitual;
	}

	public String getDespacho() {
		return this.despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<HorarioTutoriasDB> getHorariosTutorias() {
		return this.horariosTutorias;
	}

	public void setHorariosTutorias(List<HorarioTutoriasDB> horariosTutorias) {
		this.horariosTutorias = horariosTutorias;
	}

}