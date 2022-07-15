package es.uva.inf.tutorias.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.uva.inf.tutorias.business.domain.exceptions.TitulacionNotFoundException;
import es.uva.inf.tutorias.business.domain.models.Titulacion;
import es.uva.inf.tutorias.business.services.TitulacionService;
import es.uva.inf.tutorias.rest.exceptions.TitulacionBadRequestException;

@RestController
public class TitulacionController {

	@Autowired
	private TitulacionService titulacionService;
	
	@GetMapping(path = "/titulaciones")
	List<Titulacion> getTitulaciones() {
		return titulacionService.getTitulaciones();
	}
	
	@GetMapping(path = "/usuarios/{identificador}/titulaciones")
	List<Titulacion> getTitulaciones(@PathVariable String identificador) {
		try {
			return titulacionService.getTitulaciones(identificador);
		} catch (TitulacionNotFoundException e) {
			throw new TitulacionBadRequestException(e.getMessage(), e);
		}
	}
	
	@GetMapping(path = "/titulaciones/{codigoTitulacion}")
	Titulacion getTitulacion(@PathVariable Integer codigoTitulacion) {
		try {
			return titulacionService.getTitulacion(codigoTitulacion);
		} catch (TitulacionNotFoundException e) {
			throw new TitulacionBadRequestException(e.getMessage(), e);
		}
	}

}
