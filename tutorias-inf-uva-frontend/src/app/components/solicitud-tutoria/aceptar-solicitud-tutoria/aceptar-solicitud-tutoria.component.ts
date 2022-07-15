import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { forkJoin } from 'rxjs';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';
import { TutoriaService } from 'src/app/services/tutoria.service';
import { DURACIONES_ESTIMADAS, MOMENT_TIME_FORMAT } from 'src/app/utils/constantes.utils';

@Component({
  selector: 'app-aceptar-solicitud-tutoria',
  templateUrl: './aceptar-solicitud-tutoria.component.html',
  styleUrls: ['./aceptar-solicitud-tutoria.component.scss']
})
export class AceptarSolicitudTutoriaComponent implements OnInit {
  readonly aceptarSolicitudKey = "aceptarSolicitudKey";
  readonly duraciones = DURACIONES_ESTIMADAS;

  private solicitudTutoria!: SolicitudTutoria;
  private solicitudesAceptadas: SolicitudTutoria[] = [];
  private tutorias: Tutoria[] = [];

  aceptarSolicitudForm: FormGroup = this.fb.group({
    lugar: [null, Validators.required],
    comentario: [null],
    horaInicio: [null],
    duracionEstimada: [null]
  });

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private tutoriaService: TutoriaService,
    private sesionService: SesionService,
    private messageService: MessageService,
    private router: Router,
    private fb: FormBuilder,
    private dialogRef: DynamicDialogRef,
    private dialogConfig: DynamicDialogConfig
  ) { }

  ngOnInit(): void {
    if (this.dialogConfig.data) {
      this.solicitudTutoria = this.dialogConfig.data.solicitudTutoria;
      const usuarioLogueado = this.sesionService.getUsuarioLogueado();
      const solicitudesAceptadas = this.solicitudTutoriaService.getSolicitudesTutoria(usuarioLogueado!, {
        estadoSolicitud: EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.ACEPTADA),
        fechaTutoria: this.solicitudTutoria.fechaTutoria as string,
        horaInicio: this.solicitudTutoria.horaInicio as string,
        horaFin: this.solicitudTutoria.horaFin as string
      });

      const tutorias = this.tutoriaService.getTutorias(usuarioLogueado!, {
        fechaTutoria: this.solicitudTutoria.fechaTutoria as string,
        horaInicio: this.solicitudTutoria.horaInicio as string,
        horaFin: this.solicitudTutoria.horaFin as string
      });

      forkJoin([solicitudesAceptadas, tutorias]).subscribe(results => {
        this.solicitudesAceptadas = results[0];
        this.tutorias = results[1];

        if (this.solicitudesAceptadas.length > 0 || this.tutorias.length > 0) {
          const message = 'Tienes tutorias o solicitudes por confirmar en el espacio de tiempo de esta solicitud';
          this.messageService.showWarningMessage(message, this.aceptarSolicitudKey);
          this.hacerCamposObligatorios();
        }
      });
    }
  }

  onSubmit(formValues: any): void {
    this.solicitudTutoria.ubicacionTutoria = formValues.lugar;
    this.solicitudTutoria.comentarioProfesor = formValues.comentario;

    if (this.nuevaPropuestaHorario()) {
      this.solicitudTutoria.horaInicio = moment(new Date(formValues.horaInicio)).format(MOMENT_TIME_FORMAT);
      const duracionEstimada = Number(formValues.duracionEstimada.minutos);
      this.solicitudTutoria.horaFin = moment(new Date(formValues.horaInicio)).add(duracionEstimada, 'm').format(MOMENT_TIME_FORMAT);
      this.solicitudTutoria.propuestaNuevoHorario = true;
    }

    this.dialogRef.close(this.solicitudTutoria);
  }

  cancelar() {
    this.dialogRef.close();
  }

  private hacerCamposObligatorios() {
    let comentarioControl = this.aceptarSolicitudForm.controls['comentario'];
    let horaInicioControl = this.aceptarSolicitudForm.controls['horaInicio'];
    let duracionEstimadaControl = this.aceptarSolicitudForm.controls['duracionEstimada'];
    comentarioControl.addValidators(Validators.required);
    comentarioControl.updateValueAndValidity();
    horaInicioControl.addValidators(Validators.required);
    horaInicioControl.updateValueAndValidity();
    duracionEstimadaControl.addValidators(Validators.required);
    duracionEstimadaControl.updateValueAndValidity();
  }

  nuevaPropuestaHorario() {
    return this.solicitudesAceptadas.length > 0 || this.tutorias.length > 0;
  }
}
