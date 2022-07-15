package es.uva.inf.tutorias.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.ProfesorDB;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorDB, String> {

	@Query("SELECT p FROM ProfesorDB p WHERE :nombre IS null OR REPLACE(LOWER(CONCAT(p.nombre, ' ', p.apellidos)), ' ', '') LIKE LOWER(CONCAT('%', :nombre, '%'))")
	List<ProfesorDB> findBy(String nombre);
	
	ProfesorDB findByIdentificador(String identificador);
	
	List<ProfesorDB> findByAsignaturasCodigo(Integer codigo);

	List<ProfesorDB> findByTitulacionesCodigo(Integer codigo);
}
