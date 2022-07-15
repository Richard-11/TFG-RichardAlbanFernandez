import { Component, Input, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { Tutoria } from 'src/app/models/tutoria';
import { Usuario } from 'src/app/models/usuario';
import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-listado-tutorias',
  templateUrl: './listado-tutorias.component.html',
  styleUrls: ['./listado-tutorias.component.scss']
})
export class ListadoTutoriasComponent implements OnInit {

  @Input() tutorias: Tutoria[] = [];
  usuarioLogueado!: Usuario | null;

  constructor(
    private sesionService: SesionService,
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();
  }

  isAlumnoLogueado(): boolean {
    return this.usuarioLogueado instanceof Alumno;
  }

  getNombreCompleto(usuario: Usuario): string {
    return `${usuario.nombre} ${usuario.apellidos}`;
  }
}
