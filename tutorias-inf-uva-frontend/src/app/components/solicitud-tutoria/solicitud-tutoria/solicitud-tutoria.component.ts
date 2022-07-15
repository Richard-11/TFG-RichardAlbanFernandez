import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { Alumno } from 'src/app/models/alumno';
import { Asignatura } from 'src/app/models/asignatura';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Usuario } from 'src/app/models/usuario';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';
import { AceptarSolicitudTutoriaComponent } from '../aceptar-solicitud-tutoria/aceptar-solicitud-tutoria.component';
import { RechazarSolicitudTutoriaComponent } from '../rechazar-solicitud-tutoria/rechazar-solicitud-tutoria.component';

@Component({
  selector: 'app-solicitud-tutoria',
  templateUrl: './solicitud-tutoria.component.html',
  styleUrls: ['./solicitud-tutoria.component.scss'],
})
export class SolicitudTutoriaComponent implements OnInit {
  readonly solicitudTutoriaKey = 'solicitudTutoriaKey';

  solicitudTutoria!: SolicitudTutoria;
  usuario!: Usuario;
  asignatura!: Asignatura;

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private sesionService: SesionService,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    const solicitudId = Number(this.route.snapshot.paramMap.get('solicitudId'));
    const usuarioLogueado = this.sesionService.getUsuarioLogueado();

    if (solicitudId == null || usuarioLogueado == null) {
      return;
    }

    this.solicitudTutoriaService.getSolicitudTutoria(usuarioLogueado, solicitudId).subscribe((solicitud: SolicitudTutoria) => {
      this.solicitudTutoria = solicitud;
      this.asignatura = this.solicitudTutoria.asignatura!;

      if (this.isAlumnoLogueado()) {
        this.usuario = this.solicitudTutoria.profesor!;
      }

      if (this.isProfesorLogueado()) {
        this.usuario = this.solicitudTutoria.alumno!;
      }
    });
  }

  async cancelarSolicitudTutoria(): Promise<void> {
    if (await this.confirmCancelarSolicitudTutoria()) {
      this.solicitudTutoriaService.cancelarSolicitudTutoria(this.solicitudTutoria).subscribe(solicitud => {
        if (solicitud) {
          this.solicitudTutoria = solicitud;
          this.messageService.showSuccessMessage('La solicitud de tutoría se ha cancelado');
        }
      });
    }
  }

  aceptarSolicitudTutoria(): void {
    const dialogRef = this.dialogService.open(AceptarSolicitudTutoriaComponent, {
      header: 'Aceptar solicitud',
      width: '60%',
      data: {
        solicitudTutoria: { ...this.solicitudTutoria }
      }
    });

    dialogRef.onClose.subscribe((solicitud: SolicitudTutoria) => {
      if (solicitud) {
        this.aceptarSolicitud(solicitud);
      }
    });
  }

  rechazarSolicitudTutoria(): void {
    const dialogRef = this.dialogService.open(RechazarSolicitudTutoriaComponent, {
      header: 'Rechazar solicitud',
      width: '60%',
    });

    dialogRef.onClose.subscribe((motivoRechazo: string) => {
      if (motivoRechazo) {
        const solicitudRechazada = { ...this.solicitudTutoria };
        solicitudRechazada.motivoRechazo = motivoRechazo;
        this.rechazarSolicitud(solicitudRechazada);
      }
    })
  }

  async confirmarSolicitudTutoria(): Promise<void> {
    if (await this.confirmConfirmarSolicitudTutoria()) {
      this.solicitudTutoriaService.confirmarSolicitudTutoria(this.solicitudTutoria).subscribe(solicitud => {
        if (solicitud) {
          this.solicitudTutoria = solicitud;
          this.messageService.showSuccessMessage('La solicitud se ha confirmado');
        }
      })
    }
  }

  isSolicitudPendiente() {
    return this.solicitudTutoria.estadoSolicitud === EstadoSolicitudEnum.PENDIENTE;
  }

  isSolicitudAceptada() {
    return this.solicitudTutoria.estadoSolicitud === EstadoSolicitudEnum.ACEPTADA;
  }

  isSolicitudRechazada() {
    return this.solicitudTutoria.estadoSolicitud === EstadoSolicitudEnum.RECHAZADA;
  }

  isAlumnoLogueado(): boolean {
    return this.sesionService.getUsuarioLogueado() instanceof Alumno;
  }

  isProfesorLogueado(): boolean {
    return this.sesionService.getUsuarioLogueado() instanceof Profesor;
  }

  colorEstadoSolicitud(estadoSolicitud: EstadoSolicitudEnum | EstadoSolicitud | undefined) {
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

  textDanger(): string {
    return this.solicitudTutoria.propuestaNuevoHorario ? 'text-danger' : '';
  }

  private aceptarSolicitud(solicitudTutoria: SolicitudTutoria) {
    this.solicitudTutoriaService.aceptarSolicitudTutoria(solicitudTutoria).subscribe(solicitud => {
      if (solicitud) {
        this.solicitudTutoria = solicitud;
        this.messageService.showSuccessMessage('Solicitud aceptada con éxito');
      }
    });
  }

  private rechazarSolicitud(solicitudTutoria: SolicitudTutoria) {
    this.solicitudTutoriaService.rechazarSolicitudTutoria(solicitudTutoria).subscribe(solicitud => {
      if (solicitud) {
        this.solicitudTutoria = solicitud;
        this.messageService.showSuccessMessage('Solicitud rechazada con éxito');
      }
    });
  }

  private confirmCancelarSolicitudTutoria(): Promise<boolean> {
    const message = '¿Estás seguro de que quieres cancelar la solicitud de tutoría?';
    return this.messageService.showConfirmDialog(message, this.solicitudTutoriaKey);
  }

  private confirmConfirmarSolicitudTutoria(): Promise<boolean> {
    const message = 'Vas a confirmar tu asistencia a la tutoría, ¿quieres continuar?';
    return this.messageService.showConfirmDialog(message, this.solicitudTutoriaKey);
  }
}