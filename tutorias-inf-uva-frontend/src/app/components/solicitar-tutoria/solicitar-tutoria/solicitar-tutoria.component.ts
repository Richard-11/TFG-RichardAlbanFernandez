import { WeekDay } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { forkJoin } from 'rxjs';
import { Alumno } from 'src/app/models/alumno';
import { Asignatura } from 'src/app/models/asignatura';
import { HorarioTutorias } from 'src/app/models/horario-tutorias';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { AsignaturaService } from 'src/app/services/asignatura.service';
import { HorarioTutoriasService } from 'src/app/services/horario-tutorias.service';
import { MessageService } from 'src/app/services/message.service';
import { ProfesorService } from 'src/app/services/profesor.service';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';
import { DURACIONES_ESTIMADAS, MOMENT_DATETIME_FORMAT, MOMENT_DATE_FORMAT, MOMENT_TIME_FORMAT } from 'src/app/utils/constantes.utils';

@Component({
  selector: 'app-solicitar-tutoria',
  templateUrl: './solicitar-tutoria.component.html',
  styleUrls: ['./solicitar-tutoria.component.scss']
})
export class SolicitarTutoriaComponent implements OnInit {

  readonly solicitarTutoriasKey = 'solicitarTutoriasKey'
  displayHorariosDialog: boolean = false;
  fechaMinima: Date = new Date();
  finesDeSemana = [WeekDay.Saturday, WeekDay.Sunday];

  private asignaturasAlumno: Asignatura[] = [];
  asignaturas: Asignatura[] = [];
  profesorSeleccionado: boolean = false;
  profesoresFiltrados: Profesor[] = [];
  profesoresTitulacion: Profesor[] = [];
  duraciones = DURACIONES_ESTIMADAS;

  solicitudForm: FormGroup = this.fb.group({
    profesor: [null, Validators.required],
    asignatura: [{ value: null, disabled: true }],
    grupal: [false],
    fechaTutoria: [null, Validators.required],
    horaInicio: [null, Validators.required],
    duracionEstimada: [null, Validators.required],
    asunto: [null, Validators.required],
    comentario: [null, Validators.required]
  });

  comentarioRequired = true;
  horariosTutorias: HorarioTutorias[] = [];

  constructor(
    private solicitudService: SolicitudTutoriaService,
    private profesorService: ProfesorService,
    private asignaturaService: AsignaturaService,
    private horarioTutoriasService: HorarioTutoriasService,
    private sesionService: SesionService,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
  ) { }

  ngOnInit(): void {
    const codigoTitulacion = this.sesionService.getUsuarioLogueado()!.titulaciones[0].codigo;
    const profesoresTitulacion = this.profesorService.getProfesoresTitulacion(codigoTitulacion!);
    const asignaturasAlumno = this.asignaturaService.getAsignaturasUsuario(this.sesionService.getUsuarioLogueado()!.identificador);
    forkJoin([profesoresTitulacion, asignaturasAlumno]).subscribe(results => {
      if (results) {
        this.profesoresTitulacion = results[0];
        this.asignaturasAlumno = results[1];
      }
    });

    let identificadorProfesor = this.route.snapshot.queryParamMap.get('profesor');

    if (identificadorProfesor) {
      this.profesorService.getProfesor(identificadorProfesor).subscribe(profesor => {
        this.solicitudForm.patchValue({ profesor: profesor });
        this.buscarAsignaturas(profesor);
      });
    }
  }

  async onSubmit(formValues: any): Promise<void> {
    const asunto = formValues.asunto;
    const comentario = formValues.comentario;
    const fechaSolicitud = new Date();
    const fechaTutoria = moment(formValues.fechaTutoria).format(MOMENT_DATE_FORMAT);
    const horaInicio = moment(new Date(formValues.horaInicio)).format(MOMENT_TIME_FORMAT);
    const duracionEstimada = Number(formValues.duracionEstimada.minutos);
    const horaFin = moment(new Date(formValues.horaInicio)).add(duracionEstimada, 'm').format(MOMENT_TIME_FORMAT);
    const grupal = formValues.grupal;
    const alumno: Alumno = this.sesionService.getUsuarioLogueado() as Alumno;
    const profesor: Profesor = formValues.profesor;
    const asignatura: Asignatura = formValues.asignatura;

    let nuevaSolicitud: SolicitudTutoria = new SolicitudTutoria({
      asunto: asunto,
      comentarioAlumno: comentario,
      fechaSolicitud: fechaSolicitud,
      fechaTutoria: fechaTutoria,
      horaInicio: horaInicio,
      horaFin: horaFin,
      grupal: grupal,
      alumno: alumno,
      profesor: profesor,
      asignatura: asignatura
    });

    let hacerSolicitud = await this.confirmarHacerSolicitud(nuevaSolicitud);
    if (hacerSolicitud) {
      this.hacerSolicitud(nuevaSolicitud);
    }
  }

  filtrarProfesor(event: any): void {
    let filtrados: Profesor[] = [];
    let query = event.query.replace(/\s+/g, '');

    this.profesoresTitulacion.forEach(profesor => {
      let nombreProfesor = profesor.nombre + '' + profesor.apellidos;
      let identificador = profesor.identificador;

      if (nombreProfesor.toLowerCase().includes(query.toLowerCase()) ||
        identificador?.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtrados.push(profesor);
      }
    });

    this.profesoresFiltrados = filtrados;
  }

  profesorConversionMethod(profesor: Profesor): string {
    return profesor.nombre + ' ' + profesor.apellidos;
  }

  buscarAsignaturas(profesor: Profesor): void {
    this.asignaturaService.getAsignaturasUsuario(profesor.identificador!).subscribe(asignaturas => {
      const asignaturasProfesor = asignaturas;
      this.asignaturas = this.asignaturaService.getAsignaturasEnComun(asignaturasProfesor, this.asignaturasAlumno);
    });

    this.gestionarFormulario();
  }

  gestionarFormulario(): void {
    let profesorFormControl = this.solicitudForm.controls['profesor'];
    let asignaturaFormControl = this.solicitudForm.controls['asignatura'];

    if (profesorFormControl.value) {
      let profesor: Profesor = profesorFormControl.value;
      asignaturaFormControl.enable();
      this.profesorSeleccionado = true;
      this.horarioTutoriasService.getHorariosTutorias(profesor).subscribe((horarios: HorarioTutorias[]) => {
        if (horarios) {
          this.horariosTutorias = horarios;
        }
      });
    } else {
      asignaturaFormControl.reset();
      asignaturaFormControl.disable();
      this.profesorSeleccionado = false;
      this.horariosTutorias = [];
    }
  }

  onClear(): void {
    let profesorFormControl = this.solicitudForm.controls['profesor'];
    let asignaturaFormControl = this.solicitudForm.controls['asignatura'];
    let comentarioFormControl = this.solicitudForm.controls['comentario'];
    profesorFormControl.reset();
    asignaturaFormControl.reset();
    asignaturaFormControl.disable();
    this.horariosTutorias = [];
    this.profesorSeleccionado = false;

    this.comentarioRequired = true;
    comentarioFormControl.addValidators(Validators.required);

    comentarioFormControl.updateValueAndValidity();
  }

  gestionarComentarioObligatorio(): void {
    let asignaturaFormControl = this.solicitudForm.controls['asignatura'];
    let comentarioFormControl = this.solicitudForm.controls['comentario'];

    if (asignaturaFormControl.value) {
      this.comentarioRequired = false;
      comentarioFormControl.clearValidators();
    } else {
      this.comentarioRequired = true;
      comentarioFormControl.addValidators(Validators.required);
      this.messageService.showWarningMessage("Si no seleccionas una asignatura, debes rellenar el campo 'Comentario'.")
    }

    comentarioFormControl.updateValueAndValidity();
  }

  verHorariosTutorias(): void {
    this.displayHorariosDialog = true;
  }

  private confirmarHacerSolicitud(nuevaSolicitud: SolicitudTutoria): Promise<boolean> {
    return new Promise(async resolve => {
      let hacerSolicitud = true;

      if (!this.horarioTutoriasService.isSolicitudDentroHorarioTutoriasProfesor(nuevaSolicitud, this.horariosTutorias)) {
        hacerSolicitud = await this.confirmSolicitudFueraHorario();
      }

      const fechaTutoriaMoment = moment(`${nuevaSolicitud.fechaTutoria} ${nuevaSolicitud.horaInicio}`, MOMENT_DATETIME_FORMAT);
      const fechaSolicitudMoment = moment(nuevaSolicitud.fechaSolicitud);
      const diferenciaDias = fechaTutoriaMoment.diff(fechaSolicitudMoment, 'days', true);

      if ((this.isInWeekend(nuevaSolicitud.fechaSolicitud!) && fechaTutoriaMoment.toDate().getDay() === WeekDay.Monday && diferenciaDias <= 3) || diferenciaDias <= 1) {
        hacerSolicitud = await this.confirmSolicitudPosiblementeNoAtendida();
      }

      resolve(hacerSolicitud);
    });
  }

  private isInWeekend(date: Date) {
    return date.getDay() === WeekDay.Friday || date.getDay() === WeekDay.Saturday || date.getDay() === WeekDay.Sunday;
  }

  private async hacerSolicitud(nuevaSolicitud: SolicitudTutoria): Promise<void> {
    if (await this.showConfirmSolicitudTutoriaDialog(nuevaSolicitud)) {
      this.solicitudService.nuevaSolicitudTutoria(nuevaSolicitud).subscribe(solicitud => {
        if (solicitud) {
          this.messageService.showSuccessMessage('La solicitud de tutoría se ha realizado con éxito');
          this.router.navigate(['/solicitudes', solicitud.id]);
        }
      });
    }
  }

  private confirmSolicitudFueraHorario(): Promise<boolean> {
    const message = 'La tutoría a solicitar está fuera del horario de tutorías del profesor, ¿quieres realizar la solicitud de todas maneras?';
    return this.messageService.showConfirmDialog(message, this.solicitarTutoriasKey);
  }

  private confirmSolicitudPosiblementeNoAtendida(): Promise<boolean> {
    const message = 'La solicitud puede no ser atendida a tiempo, ¿quieres continuar?';
    return this.messageService.showConfirmDialog(message, this.solicitarTutoriasKey);
  }

  private showConfirmSolicitudTutoriaDialog(solicitud: SolicitudTutoria): Promise<boolean> {
    const fechaTutoria = moment(solicitud.fechaTutoria, MOMENT_DATE_FORMAT).format('DD-MM-YYYY');
    const message = `¿Quieres solicitar una tutoría para el día ${fechaTutoria} de ${solicitud.horaInicio}h a ${solicitud.horaFin}h?`;
    return this.messageService.showConfirmDialog(message, this.solicitarTutoriasKey);
  }
}
