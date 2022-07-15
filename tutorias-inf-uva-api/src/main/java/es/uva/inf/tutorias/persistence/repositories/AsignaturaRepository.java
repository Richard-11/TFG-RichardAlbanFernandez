package es.uva.inf.tutorias.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.AsignaturaDB;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaDB, Integer> {
	
	@Query("SELECT DISTINCT a FROM AsignaturaDB a JOIN a.cursos c JOIN c.titulacion t LEFT JOIN a.asignaturasMencion am "
			+ "WHERE (:codigoTitulacion IS null OR t.codigo = :codigoTitulacion) AND (:cursoId IS null OR c.id = :cursoId) AND (:mencionId IS null OR am.mencion.id = :mencionId) AND (am.curso.id IS null OR am.curso.id = c.id) AND (:nombre IS null OR REPLACE(LOWER(a.nombre), ' ', '') LIKE LOWER(CONCAT('%', :nombre, '%'))) ORDER BY a.codigo")
	List<AsignaturaDB> findBy(Integer codigoTitulacion, Integer cursoId, Integer mencionId, String nombre);
	
	@Query("SELECT DISTINCT a FROM AsignaturaDB a JOIN a.usuarios u WHERE u.identificador = :identificador AND (:nombre IS null OR REPLACE(LOWER(a.nombre), ' ', '') LIKE LOWER(CONCAT('%', :nombre, '%'))) ORDER BY a.codigo")
	List<AsignaturaDB> findByIdentificadorUsuarioAndNombreAsignatura(String identificador, String nombre);
}
