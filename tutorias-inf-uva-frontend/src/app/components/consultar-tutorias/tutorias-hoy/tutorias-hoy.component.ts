import { Component, OnInit } from '@angular/core';
import { Tutoria } from 'src/app/models/tutoria';
import { SesionService } from 'src/app/services/sesion.service';
import { TutoriaService } from 'src/app/services/tutoria.service';

@Component({
  selector: 'app-tutorias-hoy',
  templateUrl: './tutorias-hoy.component.html',
  styleUrls: ['./tutorias-hoy.component.scss']
})
export class TutoriasHoyComponent implements OnInit {

  tutorias: Tutoria[] = [];

  constructor(
    private tutoriaService: TutoriaService,
    private sesionService: SesionService
  ) { }

  ngOnInit(): void {
    const usuarioLogueado = this.sesionService.getUsuarioLogueado();
    if (usuarioLogueado == null) {
      return;
    }

    this.tutoriaService.getTutoriasHoy(usuarioLogueado).subscribe(tutoriasHoy => {
      this.tutorias = tutoriasHoy;
    });
  }

}
