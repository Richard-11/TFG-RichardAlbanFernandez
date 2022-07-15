import { WeekDay } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import * as moment from 'moment';
import { forkJoin } from 'rxjs';
import { Alumno } from 'src/app/models/alumno';
import { Asignatura } from 'src/app/models/asignatura';
import { FiltrosTutoria } from 'src/app/models/filtros-tutoria';
import { Profesor } from 'src/app/models/profesor';
import { Usuario } from 'src/app/models/usuario';
import { AsignaturaService } from 'src/app/services/asignatura.service';
import { ProfesorService } from 'src/app/services/profesor.service';
import { SesionService } from 'src/app/services/sesion.service';
import { ESTADOS_SOLICITUD, MOMENT_DATE_FORMAT } from 'src/app/utils/constantes.utils';

@Component({
  selector: 'app-filtros-solicitud-tutoria',
  templateUrl: './filtros-solicitud-tutoria.component.html',
  styleUrls: ['./filtros-solicitud-tutoria.component.scss']
})
export class FiltrosSolicitudTutoriaComponent implements OnInit {

  @Output() onSearch = new EventEmitter<FiltrosTutoria>();

  usuarioLogueado: Usuario | null = null;;
  finesDeSemana = [WeekDay.Saturday, WeekDay.Sunday];
  profesoresFiltrados: Profesor[] = [];
  profesoresTitulacion: Profesor[] = [];
  asignaturas: Asignatura[] = [];
  private asignaturasProfesor: Asignatura[] = [];
  private asignaturasAlumno: Asignatura[] = [];
  asignaturasComun: Asignatura[] = [];

  estados = ESTADOS_SOLICITUD;

  filtrosForm: FormGroup = this.fb.group({
    estado: [null],
    fechaTutoria: [null],
    grupal: [null],
    profesor: [null],
    asignatura: [null]
  });

  constructor(
    private sesionService: SesionService,
    private profesorService: ProfesorService,
    private asignaturaService: AsignaturaService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();
    if (this.usuarioLogueado != null) {
      if (this.isAlumnoLogueado()) {
        const codigoTitulacion = this.usuarioLogueado.titulaciones[0].codigo;
        const profesoresTitulacion = this.profesorService.getProfesoresTitulacion(codigoTitulacion!);
        const asignaturasAlumno = this.asignaturaService.getAsignaturasUsuario(this.usuarioLogueado.identificador);

        forkJoin([profesoresTitulacion, asignaturasAlumno]).subscribe(results => {
          if (results) {
            this.profesoresTitulacion = results[0];
            this.asignaturasAlumno = results[1];
            this.asignaturas = this.asignaturasAlumno;
          }
        });
      } else if (this.isProfesorLogueado()) {
        this.asignaturaService.getAsignaturasUsuario(this.usuarioLogueado.identificador).subscribe(asignaturas => {
          this.asignaturasProfesor = asignaturas;
          this.asignaturas = this.asignaturasProfesor;
        });
      }
    }
  }

  onSubmit(values: any): void {
    let fechaTutoria = values.fechaTutoria;
    if (fechaTutoria) {
      fechaTutoria = moment(values.fechaTutoria).format(MOMENT_DATE_FORMAT);
    }

    let filtros: FiltrosTutoria = {
      estadoSolicitud: values.estado,
      fechaTutoria: fechaTutoria,
      grupal: values.grupal,
      profesor: values.profesor,
      asignatura: values.asignatura
    }

    this.onSearch.emit(filtros);
  }

  filtrarProfesor(event: any) {
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

  buscarAsignaturas(profesor: Profesor): void {
    this.asignaturaService.getAsignaturasUsuario(profesor.identificador!).subscribe(asignaturas => {
      this.asignaturasProfesor = asignaturas;
      this.asignaturas = this.asignaturaService.getAsignaturasEnComun(this.asignaturasProfesor, this.asignaturasAlumno);
    });
  }

  gestionarProfesorFormulario(): void {
    if (this.usuarioLogueado != null) {
      let profesorFormControl = this.filtrosForm.controls['profesor'];
      let asignaturaFormControl = this.filtrosForm.controls['asignatura'];

      if (this.isAlumnoLogueado()) {
        if (profesorFormControl.value == null) {
          asignaturaFormControl.reset();
          this.asignaturas = this.asignaturasAlumno;
        }
      }
    }
  }

  onClear(): void {
    let profesorFormControl = this.filtrosForm.controls['profesor'];
    let asignaturaFormControl = this.filtrosForm.controls['asignatura'];
    profesorFormControl.reset();
    asignaturaFormControl.reset();

    if (this.isAlumnoLogueado()) {
      this.asignaturas = this.asignaturasAlumno;
    }
  }

  profesorConversionMethod(profesor: Profesor): string {
    return profesor.nombre + ' ' + profesor.apellidos;
  }

  obtenerLabelGrupal(values: any): string {
    const grupal = values.grupal;

    if (grupal == null) {
      return '';
    }

    return grupal ? 'Si' : 'No';
  }

  isAlumnoLogueado() {
    return this.usuarioLogueado instanceof Alumno;
  }

  isProfesorLogueado() {
    return this.usuarioLogueado instanceof Profesor;
  }
}
