package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the franjatutorias database table.
 * 
 */
@Entity
@Table(name = "franjatutorias")
public class FranjaTutoriasDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FRANJATUTORIAS_ID_GENERATOR", sequenceName = "SECUENCIA_ID_FRANJATUTORIAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRANJATUTORIAS_ID_GENERATOR")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "diaid")
	private DiaDB dia;

	@Column(name = "horainicio")
	private LocalTime horaInicio;

	@Column(name = "horafin")
	private LocalTime horaFin;

	private String centro;

	private String despacho;

	@ManyToOne
	@JoinColumn(name = "horariotutoriasid")
	private HorarioTutoriasDB horarioTutorias;

	public FranjaTutoriasDB() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiaDB getDia() {
		return this.dia;
	}

	public void setDia(DiaDB dia) {
		this.dia = dia;
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public HorarioTutoriasDB getHorarioTutorias() {
		return horarioTutorias;
	}

	public void setHorarioTutorias(HorarioTutoriasDB horarioTutorias) {
		this.horarioTutorias = horarioTutorias;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FranjaTutoriasDB))
			return false;
		return id != null && id.equals(((FranjaTutoriasDB) obj).getId());
	}

}