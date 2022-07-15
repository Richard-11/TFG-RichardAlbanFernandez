import { Component, OnInit } from '@angular/core';
import { CalendarOptions, EventClickArg, EventInput } from '@fullcalendar/angular';
import esLocale from '@fullcalendar/core/locales/es';
import { DialogService } from 'primeng/dynamicdialog';
import { forkJoin } from 'rxjs';
import { Alumno } from 'src/app/models/alumno';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { Usuario } from 'src/app/models/usuario';
import { EventService } from 'src/app/services/event.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';
import { TutoriaService } from 'src/app/services/tutoria.service';
import { DetalleSolicitudPendienteConfirmarComponent } from '../detalle-solicitud-pendiente-confirmar/detalle-solicitud-pendiente-confirmar.component';
import { DetalleTutoriaComponent } from '../detalle-tutoria/detalle-tutoria.component';

@Component({
  selector: 'app-calendario-tutorias',
  templateUrl: './calendario-tutorias.component.html',
  styleUrls: ['./calendario-tutorias.component.scss']
})
export class CalendarioTutoriasComponent implements OnInit {
  private usuarioLogueado: Usuario | null = null;

  events: EventInput[] = [];

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    locale: esLocale,
    weekends: false,
    allDaySlot: false,
    nowIndicator: true,
    eventClick: this.handleEventClick.bind(this)
  }

  constructor(
    private tutoriaService: TutoriaService,
    private solicitudTutoriaService: SolicitudTutoriaService,
    private sesionService: SesionService,
    private eventService: EventService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();

    if (this.usuarioLogueado == null) {
      return;
    }

    if (this.usuarioLogueado instanceof Alumno) {
      this.tutoriaService.getTutorias(this.usuarioLogueado, { cancelada: false }).subscribe(tutorias => {
        if (tutorias) {
          this.events = this.eventService.convertirTutoriasEnEventos(tutorias);
          this.calendarOptions.events = this.events;
        }
      })
    }

    if (this.usuarioLogueado instanceof Profesor) {
      const tutoriasProfesor = this.tutoriaService.getTutorias(this.usuarioLogueado, { cancelada: false });
      const solicitudesPendientesConfirmar = this.solicitudTutoriaService.getSolicitudesTutoria(this.usuarioLogueado, {
        estadoSolicitud: EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.ACEPTADA)
      });

      forkJoin([tutoriasProfesor, solicitudesPendientesConfirmar]).subscribe(results => {
        if (results) {
          const tutoriasEvents = this.eventService.convertirTutoriasEnEventos(results[0]);
          const solicitudesEvents = this.eventService.convertirSolicitudesAceptadasEnEventos(results[1]);
          this.events = tutoriasEvents.concat(solicitudesEvents);

          this.calendarOptions.events = this.events;
        }
      });
    }
  }

  handleEventClick(clickInfo: EventClickArg) {
    const tutoria = clickInfo.event.extendedProps["tutoria"];
    if (tutoria) {
      this.mostrarDetalleTutoria(tutoria);
    }

    const solicitud = clickInfo.event.extendedProps["solicitud"];
    if (solicitud) {
      this.mostrarDetalleSolicitud(solicitud);
    }
  }

  private mostrarDetalleTutoria(tutoria: Tutoria): void {
    this.dialogService.open(DetalleTutoriaComponent, {
      header: 'Detalle tutoría',
      width: '500px',
      data: {
        tutoria: { ...tutoria }
      }
    });
  }

  private mostrarDetalleSolicitud(solicitud: SolicitudTutoria): void {
    this.dialogService.open(DetalleSolicitudPendienteConfirmarComponent, {
      header: 'Detalle solicitud pendiente de confirmación',
      width: '500px',
      data: {
        solicitud: { ...solicitud }
      }
    });
  }

}
