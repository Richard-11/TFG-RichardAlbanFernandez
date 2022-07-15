import { Component, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { Usuario } from 'src/app/models/usuario';
import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-right-sidebar',
  templateUrl: './right-sidebar.component.html',
  styleUrls: ['./right-sidebar.component.scss']
})
export class RightSidebarComponent implements OnInit {

  private usuarioLogueado!: Usuario | null;

  constructor(
    private sesionService: SesionService
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();
  }

  isAlumno() {
    return this.usuarioLogueado instanceof Alumno;
  }
}
