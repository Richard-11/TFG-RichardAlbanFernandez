package es.uva.inf.tutorias.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.HorarioTutoriasDB;

@Repository
public interface HorarioTutoriasRepository extends JpaRepository<HorarioTutoriasDB, Long>{
	
	List<HorarioTutoriasDB> deleteByProfesorIdentificador(String idProfesor);

	List<HorarioTutoriasDB> findByProfesorIdentificador(String identificador);

}
