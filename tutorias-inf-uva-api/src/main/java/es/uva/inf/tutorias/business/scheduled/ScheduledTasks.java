package es.uva.inf.tutorias.business.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.uva.inf.tutorias.business.services.SolicitudTutoriaService;

@Component
public class ScheduledTasks {
	
	@Autowired
	private SolicitudTutoriaService solicitudTutoriaService;
	
	@Scheduled(cron = "${cron.rechazar.solicitudes.pendientes.caducadas}", zone = "Europe/Madrid")
	public void rechazarSolicitudesPendientesCaducadas() {
		solicitudTutoriaService.rechazarSolicitudesPendientesCaducadas();
	}
}
