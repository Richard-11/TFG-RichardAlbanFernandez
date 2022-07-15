package es.uva.inf.tutorias.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uva.inf.tutorias.persistence.entities.UsuarioDB;

public interface UsuarioRepository extends JpaRepository<UsuarioDB, String> {

	UsuarioDB findByIdentificador(String identificador);

}
