import { Component, Input, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Usuario } from 'src/app/models/usuario';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';

@Component({
  selector: 'app-listado-solicitudes-tutoria',
  templateUrl: './listado-solicitudes-tutoria.component.html',
  styleUrls: ['./listado-solicitudes-tutoria.component.scss']
})
export class ListadoSolicitudesTutoriaComponent implements OnInit {
  readonly listadoSolicitudesTutoriaKey = 'listadoSolicitudesTutoriaKey';

  @Input() solicitudes: SolicitudTutoria[] = [];
  usuarioLogueado!: Usuario | null;

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private sesionService: SesionService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();
  }

  async cancelarSolicitudTutoria(solicitud: SolicitudTutoria, index: number): Promise<void> {
    if (await this.confirmCancelarSolicitudTutoria()) {
      this.solicitudTutoriaService.cancelarSolicitudTutoria(solicitud).subscribe(solicitud => {
        if (solicitud) {
          this.solicitudes[index] = solicitud;
          this.messageService.showSuccessMessage('La solicitud de tutoría se ha cancelado');
        }
      });
    }
  }

  getNombreCompleto(usuario: Usuario): string {
    return `${usuario.nombre} ${usuario.apellidos}`;
  }

  isAlumnoLogueado(): boolean {
    return this.usuarioLogueado instanceof Alumno;
  }

  isSolicitudPendiente(solicitud: SolicitudTutoria): boolean {
    return solicitud.estadoSolicitud === EstadoSolicitudEnum.PENDIENTE;
  }

  isSolicitudAceptada(solicitud: SolicitudTutoria): boolean {
    return solicitud.estadoSolicitud === EstadoSolicitudEnum.ACEPTADA;
  }

  colorEstadoSolicitud(estadoSolicitud: EstadoSolicitudEnum | EstadoSolicitud | null): string {
    switch (estadoSolicitud) {
      case EstadoSolicitudEnum.ACEPTADA:
        return 'rounded px-2 py-1 text-light bg-success';
      case EstadoSolicitudEnum.RECHAZADA:
        return 'rounded px-2 py-1 text-light bg-danger';
      case EstadoSolicitudEnum.CONFIRMADA:
        return 'rounded px-2 py-1 text-light bg-primary';
      case EstadoSolicitudEnum.CANCELADA:
        return 'rounded px-2 py-1 text-light bg-secondary';
      default:
        return 'rounded px-2 py-1 text-light bg-warning';
    }
  }

  private confirmCancelarSolicitudTutoria(): Promise<boolean> {
    let message = '¿Estás seguro de que quieres cancelar la solicitud de tutoría?';
    return this.messageService.showConfirmDialog(message, this.listadoSolicitudesTutoriaKey);
  }
}
