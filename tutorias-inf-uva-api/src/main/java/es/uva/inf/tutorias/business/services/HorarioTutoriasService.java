package es.uva.inf.tutorias.business.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uva.inf.tutorias.business.domain.converters.HorarioTutoriasConverter;
import es.uva.inf.tutorias.business.domain.enums.TipoHorarioEnum;
import es.uva.inf.tutorias.business.domain.exceptions.HorariosTutoriasNoValidosException;
import es.uva.inf.tutorias.business.domain.models.FranjaTutorias;
import es.uva.inf.tutorias.business.domain.models.HorarioTutorias;
import es.uva.inf.tutorias.business.domain.models.Profesor;
import es.uva.inf.tutorias.persistence.entities.HorarioTutoriasDB;
import es.uva.inf.tutorias.persistence.repositories.HorarioTutoriasRepository;

@Service
public class HorarioTutoriasService {

	@Autowired
	private HorarioTutoriasRepository horarioTutoriasRepository;

	public List<HorarioTutorias> getHorariosTutorias(String identificador) {
		return horarioTutoriasRepository.findByProfesorIdentificador(identificador).stream()
				.map(horarioDB -> HorarioTutoriasConverter.convertToHorarioTutorias(horarioDB))
				.collect(Collectors.toList());
	}

	@Transactional
	public List<HorarioTutorias> nuevosHorariosTutorias(String identificador, List<HorarioTutorias> horarios)
			throws HorariosTutoriasNoValidosException {
		Boolean validos = comprobarSiHorariosValidos(horarios);

		if (!validos) {
			throw new HorariosTutoriasNoValidosException();
		}

		Profesor profesor = new Profesor();
		profesor.setIdentificador(identificador);
		horarios.forEach(h -> h.setProfesor(profesor));

		horarioTutoriasRepository.deleteByProfesorIdentificador(identificador);
		
		for (HorarioTutorias horario : horarios) {
			horario.getFranjasTutorias().sort(new Comparator<FranjaTutorias>() {
				@Override
				public int compare(FranjaTutorias o1, FranjaTutorias o2) {
					return o1.getDia().id.compareTo(o2.getDia().id);
				}
			});
		}

		List<HorarioTutoriasDB> horariosTutoriasDB = horarios.stream()
				.map(horarioTutoriasDB -> HorarioTutoriasConverter.convertToHorarioTutoriasDB(horarioTutoriasDB))
				.collect(Collectors.toList());

		return horarioTutoriasRepository.saveAll(horariosTutoriasDB).stream()
				.map(horarioDB -> HorarioTutoriasConverter.convertToHorarioTutorias(horarioDB))
				.collect(Collectors.toList());
	}

	private Boolean comprobarSiHorariosValidos(List<HorarioTutorias> horarios) {
		final int MAX_HORARIOS = 2;
		
		if (horarios.size() == 0 || horarios.size() > MAX_HORARIOS) {
			return false;
		}
		
		if (horarios.size() == MAX_HORARIOS) {
			for (HorarioTutorias horarioTutorias : horarios) {
				if (horarioTutorias == null || horarioTutorias.getTipoHorario() == null
						|| horarioTutorias.getTipoHorario().getId() == TipoHorarioEnum.ANUAL.id) {
					return false;
				}
			}

			if (horarios.get(0).getTipoHorario().getId() == TipoHorarioEnum.PRIMER_CUATRIMESTRE.id) {
				if (horarios.get(1).getTipoHorario().getId() != TipoHorarioEnum.SEGUNDO_CUATRIMESTRE.id) {
					return false;
				}
			}

			if (horarios.get(0).getTipoHorario().getId() == TipoHorarioEnum.SEGUNDO_CUATRIMESTRE.id) {
				if (horarios.get(1).getTipoHorario().getId() != TipoHorarioEnum.PRIMER_CUATRIMESTRE.id) {
					return false;
				}
			}
		}
		
		for (HorarioTutorias horario : horarios) {
			for (FranjaTutorias franja : horario.getFranjasTutorias()) {
				if (franja.getHoraInicio().compareTo(franja.getHoraFin()) >= 0) {
					return false;
				}
			}
		}

		return true;
	}

}
