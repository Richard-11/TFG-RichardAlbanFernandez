import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';
import { DialogService } from 'primeng/dynamicdialog';
import { Alumno } from 'src/app/models/alumno';
import { Asignatura } from 'src/app/models/asignatura';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { Usuario } from 'src/app/models/usuario';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';
import { TutoriaService } from 'src/app/services/tutoria.service';
import { MOMENT_DATETIME_FORMAT } from 'src/app/utils/constantes.utils';
import { CancelarTutoriaComponent } from '../cancelar-tutoria/cancelar-tutoria.component';

@Component({
  selector: 'app-tutoria',
  templateUrl: './tutoria.component.html',
  styleUrls: ['./tutoria.component.scss']
})
export class TutoriaComponent implements OnInit {
  tutoria!: Tutoria;
  solicitudTutoria!: SolicitudTutoria;
  usuario!: Usuario;
  asignatura!: Asignatura;

  constructor(
    private tutoriaService: TutoriaService,
    private sesionService: SesionService,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    const tutoriaId = Number(this.route.snapshot.paramMap.get('tutoriaId'));
    const usuarioLogueado = this.sesionService.getUsuarioLogueado();

    if (tutoriaId == null || usuarioLogueado == null) {
      return;
    }

    this.tutoriaService.getTutoria(usuarioLogueado, tutoriaId).subscribe((tutoria: Tutoria) => {
      this.tutoria = tutoria;
      this.solicitudTutoria = tutoria.solicitudTutoria!;

      if (this.solicitudTutoria.asignatura) {
        this.asignatura = this.solicitudTutoria.asignatura;
      }

      if (this.isAlumnoLogueado()) {
        this.usuario = this.solicitudTutoria.profesor!;
      }

      if (this.isProfesorLogueado()) {
        this.usuario = this.solicitudTutoria.alumno!;
      }
    });
  }

  showCancelarTutoriaDialog(): void {
    const dialogRef = this.dialogService.open(CancelarTutoriaComponent, {
      header: 'Cancelar tutoría',
      width: '60%',
    });

    dialogRef.onClose.subscribe((motivoCancelacion: string) => {
      if (motivoCancelacion) {
        const tutoriaACancelar = { ...this.tutoria };
        tutoriaACancelar.motivoCancelacion = motivoCancelacion;
        this.cancelarTutoria(tutoriaACancelar);
      }
    });
  }

  mostrarCancelarButton(): boolean {
    let currentDate = new Date();
    let tutoriaDate = moment(`${this.solicitudTutoria?.fechaTutoria} ${this.solicitudTutoria?.horaInicio}`, MOMENT_DATETIME_FORMAT).toDate();

    if (currentDate >= tutoriaDate) {
      return false;
    }

    if (this.tutoria.cancelada) {
      return false;
    }

    return true;
  }

  isAlumnoLogueado(): boolean {
    return this.sesionService.getUsuarioLogueado() instanceof Alumno;
  }

  isProfesorLogueado(): boolean {
    return this.sesionService.getUsuarioLogueado() instanceof Profesor;
  }

  private cancelarTutoria(tutoria: Tutoria) {
    this.tutoriaService.cancelarTutoria(tutoria).subscribe(tutoria => {
      if (tutoria) {
        this.tutoria = tutoria;
        this.messageService.showSuccessMessage('Tutoría cancelada con éxito');
      }
    });
  }

}
