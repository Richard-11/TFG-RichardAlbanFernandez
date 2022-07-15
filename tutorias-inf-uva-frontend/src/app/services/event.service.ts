import { Injectable } from '@angular/core';
import { EventInput } from '@fullcalendar/angular';
import * as moment from 'moment';
import { SolicitudTutoria } from '../models/solicitud-tutoria';
import { Tutoria } from '../models/tutoria';
import { MOMENT_DATETIME_FORMAT } from '../utils/constantes.utils';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor() { }

  public convertirTutoriasEnEventos(tutorias: Tutoria[]): EventInput[] {
    let events: EventInput[] = [];
    for (let tutoria of tutorias) {
      let event: EventInput = {
        title: tutoria.solicitudTutoria!.asunto ? tutoria.solicitudTutoria?.asunto : '',
        start: moment(`${tutoria.solicitudTutoria?.fechaTutoria} ${tutoria.solicitudTutoria?.horaInicio}`, MOMENT_DATETIME_FORMAT).toDate(),
        end: moment(`${tutoria.solicitudTutoria?.fechaTutoria} ${tutoria.solicitudTutoria?.horaFin}`, MOMENT_DATETIME_FORMAT).toDate(),
        extendedProps: { tutoria: tutoria }
      };

      events.push(event);
    }

    return events;
  }

  public convertirSolicitudesAceptadasEnEventos(solicitudes: SolicitudTutoria[]): EventInput[] {
    let events: EventInput[] = [];
    for (let solicitud of solicitudes) {
      let event: EventInput = {
        title: solicitud.asunto,
        start: moment(`${solicitud.fechaTutoria} ${solicitud.horaInicio}`, MOMENT_DATETIME_FORMAT).toDate(),
        end: moment(`${solicitud.fechaTutoria} ${solicitud.horaFin}`, MOMENT_DATETIME_FORMAT).toDate(),
        backgroundColor: "#8FB56B",
        extendedProps: { solicitud: solicitud }
      };

      events.push(event);
    }

    return events;
  }

}
