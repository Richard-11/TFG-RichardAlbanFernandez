package es.uva.inf.tutorias.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.business.domain.converters.AlumnoConverter;
import es.uva.inf.tutorias.business.domain.converters.ProfesorConverter;
import es.uva.inf.tutorias.business.domain.exceptions.AuthenticationFailedException;
import es.uva.inf.tutorias.business.domain.models.Usuario;
import es.uva.inf.tutorias.persistence.entities.AlumnoDB;
import es.uva.inf.tutorias.persistence.entities.ProfesorDB;
import es.uva.inf.tutorias.persistence.entities.UsuarioDB;
import es.uva.inf.tutorias.persistence.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario login(String identificador, String password) throws AuthenticationFailedException {
		UsuarioDB usuarioDB = usuarioRepository.findByIdentificador(identificador);
		
		if (usuarioDB == null || !usuarioDB.getPassword().equals(password)) {
			throw new AuthenticationFailedException();
		}
		
		if (usuarioDB instanceof AlumnoDB) {
			return AlumnoConverter.convertToAlumno((AlumnoDB) usuarioDB);
		} else {
			return ProfesorConverter.convertToProfesorLight((ProfesorDB) usuarioDB);
		}
	}

}
