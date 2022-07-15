import { Component, OnInit } from '@angular/core';
import { FiltrosTutoria } from 'src/app/models/filtros-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { SesionService } from 'src/app/services/sesion.service';
import { TutoriaService } from 'src/app/services/tutoria.service';

@Component({
  selector: 'app-filtrar-tutorias',
  templateUrl: './filtrar-tutorias.component.html',
  styleUrls: ['./filtrar-tutorias.component.scss']
})
export class FiltrarTutoriasComponent implements OnInit {
  buscarClicked = false;
  tutorias: Tutoria[] = [];

  constructor(
    private tutoriaService: TutoriaService,
    private sesionService: SesionService
  ) { }

  ngOnInit(): void {
  }

  buscarTutorias(filtros: FiltrosTutoria) {
    this.buscarClicked = true;
    const usuarioLogeado = this.sesionService.getUsuarioLogueado();

    if (usuarioLogeado)
      this.tutoriaService.getTutorias(usuarioLogeado, filtros).subscribe(tutorias => {
        this.tutorias = tutorias;
      });
  }

}