import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { Profesor } from 'src/app/models/profesor';
import { ProfesorService } from 'src/app/services/profesor.service';

@Component({
  selector: 'app-busqueda-por-profesor',
  templateUrl: './busqueda-por-profesor.component.html',
  styleUrls: ['./busqueda-por-profesor.component.scss']
})
export class BusquedaPorProfesorComponent implements OnInit {

  profesorForm: FormGroup = this.fb.group({
    nombre: [null]
  });

  clicked = false;

  profesores: Profesor[] = [];

  constructor(
    private profesorService: ProfesorService,
    private router: Router,
    private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit(values: any): void {
    this.clicked = true;

    let nombre: string = values.nombre;

    if (nombre) {
      nombre = nombre.replace(/\s+/g, '');
    }

    this.profesorService.getProfesores(nombre).subscribe(profesores => {
      this.profesores = profesores;
    });
  }

  navigateToSolicitarTutoria(identificadorProfesor: number) {
    let navigationExtras: NavigationExtras = {
      queryParams: { 'profesor': identificadorProfesor }
    };

    this.router.navigate(['/solicitar-tutoria'], navigationExtras);
  }
}
