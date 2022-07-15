import { Component, Input, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { Profesor } from 'src/app/models/profesor';
import { Usuario } from 'src/app/models/usuario';

@Component({
  selector: 'app-datos-usuario',
  templateUrl: './datos-usuario.component.html',
  styleUrls: ['./datos-usuario.component.scss']
})
export class DatosUsuarioComponent implements OnInit {

  @Input() usuario!: Usuario;
  @Input() showNif: boolean = false;

  constructor() { }

  ngOnInit(): void {
    if (this.isProfesor()) {
    }
  }

  isProfesor(): boolean {
    return this.usuario instanceof Profesor;
  }

  isAlumno(): boolean {
    return this.usuario instanceof Alumno;
  }

  get profesor(): Profesor {
    return this.usuario as Profesor;
  }

  get alumno(): Alumno {
    return this.usuario as Alumno;
  }
}
