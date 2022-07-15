import { Component, OnInit } from '@angular/core';
import { HorarioTutorias } from 'src/app/models/horario-tutorias';
import { Profesor } from 'src/app/models/profesor';
import { Usuario } from 'src/app/models/usuario';
import { HorarioTutoriasService } from 'src/app/services/horario-tutorias.service';
import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {

  usuario!: Usuario;
  horariosTutorias: HorarioTutorias[] = [];

  constructor(
    private sesionService: SesionService,
    private horarioTutoriasService: HorarioTutoriasService
  ) { }

  ngOnInit(): void {
    this.usuario = this.sesionService.getUsuarioLogueado()!;

    if (this.isProfesor()) {
      this.horarioTutoriasService.getHorariosTutorias(this.usuario as Profesor).subscribe(horarios => {
        if (horarios) {
          this.horariosTutorias = horarios;
        }
      });
    }
  }

  isProfesor(): boolean {
    return this.usuario instanceof Profesor;
  }

  get profesor(): Profesor {
    return this.usuario as Profesor;
  }
}
