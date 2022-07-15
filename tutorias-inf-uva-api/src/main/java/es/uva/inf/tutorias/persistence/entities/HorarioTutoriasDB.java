package es.uva.inf.tutorias.persistence.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the horariotutorias database table.
 * 
 */
@Entity
@Table(name = "horariotutorias")
public class HorarioTutoriasDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "HORARIOTUTORIAS_ID_GENERATOR", sequenceName = "SECUENCIA_ID_HORARIOTUTORIAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORARIOTUTORIAS_ID_GENERATOR")
	private Long id;

	@OneToMany(mappedBy = "horarioTutorias", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FranjaTutoriasDB> franjasTutorias;

	@ManyToOne
	@JoinColumn(name = "tipohorarioid")
	private TipoHorarioDB tipoHorario;

	@ManyToOne
	@JoinColumn(name = "idprofesor")
	private ProfesorDB profesor;

	public HorarioTutoriasDB() {
		franjasTutorias = new ArrayList<>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<FranjaTutoriasDB> getFranjasTutorias() {
		return this.franjasTutorias;
	}

	public void setFranjasTutorias(List<FranjaTutoriasDB> franjasTutorias) {
		this.franjasTutorias = franjasTutorias;
	}

	public void addFranjaTutorias(FranjaTutoriasDB franjaTutorias) {
		franjasTutorias.add(franjaTutorias);
		franjaTutorias.setHorarioTutorias(this);
	}

	public void removeFranjaTutorias(FranjaTutoriasDB franjaTutorias) {
		franjasTutorias.remove(franjaTutorias);
		franjaTutorias.setHorarioTutorias(null);
	}

	public TipoHorarioDB getTipoHorario() {
		return this.tipoHorario;
	}

	public void setTipoHorario(TipoHorarioDB tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public ProfesorDB getProfesor() {
		return profesor;
	}

	public void setProfesor(ProfesorDB profesor) {
		this.profesor = profesor;
	}

}