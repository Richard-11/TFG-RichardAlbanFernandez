import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { Asignatura } from 'src/app/models/asignatura';
import { CursoAcademico } from 'src/app/models/curso-academico';
import { MencionTitulacion } from 'src/app/models/mencion-titulacion';
import { Titulacion } from 'src/app/models/titulacion';
import { AsignaturaService } from 'src/app/services/asignatura.service';
import { ProfesorService } from 'src/app/services/profesor.service';
import { TitulacionService } from 'src/app/services/titulacion.service';

@Component({
  selector: 'app-busqueda-por-asignatura',
  templateUrl: './busqueda-por-asignatura.component.html',
  styleUrls: ['./busqueda-por-asignatura.component.scss']
})
export class BusquedaPorAsignaturaComponent implements OnInit {

  titulaciones: Titulacion[] = [];
  cursos: CursoAcademico[] = [];
  menciones: MencionTitulacion[] = [];
  asignaturaForm: FormGroup = this.fb.group({
    titulacion: [null, Validators.required],
    curso: [{ value: null, disabled: true }],
    mencion: [{ value: null, disabled: true }],
    nombre: ['']
  });

  clicked = false;

  asignaturas: Asignatura[] = [];

  constructor(
    private titulacionService: TitulacionService,
    private asignaturaService: AsignaturaService,
    private profesorService: ProfesorService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.titulacionService.getTitulaciones().subscribe(titulaciones => {
      this.titulaciones = titulaciones;
    });
  }

  onSubmit(values: any): void {
    let codigoTitulacion = values.titulacion!.codigo;
    let cursoId = values.curso ? values.curso.id : null;
    let mencionId = values.mencion ? values.mencion.id : null;
    let nombre = values.nombre;

    if (nombre) {
      nombre = nombre.replace(/\s+/g, '');
    }

    this.asignaturaService.getAsignaturas(codigoTitulacion, cursoId, mencionId, nombre).subscribe(asignaturas => {
      this.asignaturas = asignaturas;
      this.clicked = true;
    });
  }

  onTabOpen(event: any): void {
    let tabIndex = event.index;
    let codigoAsignatura = this.asignaturas[tabIndex].codigo;

    this.profesorService.getProfesoresAsignatura(codigoAsignatura!).subscribe(profesoresAsignatura => {
      this.asignaturas[tabIndex].usuarios = profesoresAsignatura;
    });
  }

  gestionarFormulario(values: any) {
    let cursoFormControl = this.asignaturaForm.controls['curso'];
    let mencionFormControl = this.asignaturaForm.controls['mencion'];

    this.cursos = values.titulacion?.cursosAcademicos;

    if (this.cursos && this.cursos.length > 0) {
      cursoFormControl.enable();
    } else {
      cursoFormControl.reset();
      cursoFormControl.disable();
    }

    this.menciones = values.curso?.menciones;

    if (values.titulacion && this.menciones && this.menciones.length > 0) {
      mencionFormControl.enable();
    } else {
      mencionFormControl.reset();
      mencionFormControl.disable();
    }
  }

  navigateToSolicitarTutoria(identificadorProfesor: number): void {
    let navigationExtras: NavigationExtras = {
      queryParams: { 'profesor': identificadorProfesor }
    };

    this.router.navigate(['/solicitar-tutoria'], navigationExtras);
  }
}