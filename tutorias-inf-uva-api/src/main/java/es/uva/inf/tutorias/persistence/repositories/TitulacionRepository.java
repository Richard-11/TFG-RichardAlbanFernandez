package es.uva.inf.tutorias.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.uva.inf.tutorias.persistence.entities.TitulacionDB;

@Repository
public interface TitulacionRepository extends JpaRepository<TitulacionDB, Integer> {
	
	@Query("SELECT t FROM AsignaturaDB a JOIN a.cursos c JOIN c.titulacion t WHERE t.codigo = :codigo")
	List<TitulacionDB> findByCodigoAsignatura(Integer codigo);
	
	List<TitulacionDB> findByUsuariosIdentificador(String identificador);
}
