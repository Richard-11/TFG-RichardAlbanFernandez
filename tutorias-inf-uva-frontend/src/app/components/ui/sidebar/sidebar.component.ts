import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { Alumno } from 'src/app/models/alumno';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  readonly alumnoItems = [{
    label: 'Tutorías',
    icon: 'pi pi-briefcase',
    items: [
      { label: 'Consultar tutorías', icon: 'pi pi-inbox', routerLink: 'consultar-tutorias' },
      { label: 'Calendario', icon: 'pi pi-calendar', routerLink: 'calendario-tutorias' }
    ]
  },
  {
    label: 'Solicitudes',
    icon: 'pi pi-envelope',
    items: [
      { label: 'Solicitar tutoría', icon: 'pi pi-pencil', routerLink: 'solicitar-tutoria' },
      { label: 'Consultar solicitudes', icon: 'pi pi-inbox', routerLink: 'consultar-solicitudes' }
    ]
  },
  {
    label: 'Buscar profesor',
    icon: 'pi pi-search',
    items: [
      { label: 'Búsqueda por profesor', icon: 'pi pi-users', routerLink: 'buscar-profesor/busqueda-por-profesor' },
      { label: 'Búsqueda por asignatura', icon: 'pi pi-book', routerLink: 'buscar-profesor/busqueda-por-asignatura' }
    ]
  }];

  readonly profesorItems = [{
    label: 'Tutorías',
    icon: 'pi pi-briefcase',
    items: [
      { label: 'Consultar tutorías', icon: 'pi pi-inbox', routerLink: 'consultar-tutorias' },
      { label: 'Calendario', icon: 'pi pi-calendar', routerLink: 'calendario-tutorias' }
    ]
  }, {
    label: 'Solicitudes',
    icon: 'pi pi-envelope',
    items: [
      { label: 'Consultar solicitudes', icon: 'pi pi-inbox', routerLink: 'consultar-solicitudes' }
    ],
  }];

  sidebarItems = this.alumnoItems;

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private sesionService: SesionService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    const usuarioLogeado = this.sesionService.getUsuarioLogueado();

    if (usuarioLogeado instanceof Profesor) {
      this.sidebarItems = this.profesorItems;
      this.solicitudTutoriaService.getSolicitudesTutoria(usuarioLogeado, {
        estadoSolicitud: EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.PENDIENTE)
      }).subscribe((solicitudesPendientes: SolicitudTutoria[]) => {
        if (solicitudesPendientes.length > 0) {
          this.messageService.showInfoMessage('Tienes solicitudes pendientes');
        }
      });
    }

    if (usuarioLogeado instanceof Alumno) {
      const solicitudesRechazadas = this.solicitudTutoriaService.getSolicitudesTutoria(usuarioLogeado, {
        estadoSolicitud: EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.RECHAZADA)
      });
      const solicitudesAceptadas = this.solicitudTutoriaService.getSolicitudesTutoria(usuarioLogeado, {
        estadoSolicitud: EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.ACEPTADA)
      });

      forkJoin([solicitudesRechazadas, solicitudesAceptadas]).subscribe(results => {
        if (results) {
          if (results[0]?.length > 0) {
            for (const solicitud of results[0]) {
              if (!solicitud.rechazadaVistaPorAlumno) {
                this.messageService.showInfoMessage('Tienes solicitudes que han sido rechazadas', false);
                break;
              }
            }
          }

          if (results[1]?.length > 0) {
            this.messageService.showInfoMessage('Tienes solicitudes aceptadas pendientes de confirmar', false);
          }
        }
      });
    }
  }
}
