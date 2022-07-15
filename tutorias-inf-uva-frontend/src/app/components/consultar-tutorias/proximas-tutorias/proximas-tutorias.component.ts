import { Component, OnInit } from '@angular/core';
import { Tutoria } from 'src/app/models/tutoria';
import { SesionService } from 'src/app/services/sesion.service';
import { TutoriaService } from 'src/app/services/tutoria.service';

@Component({
  selector: 'app-proximas-tutorias',
  templateUrl: './proximas-tutorias.component.html',
  styleUrls: ['./proximas-tutorias.component.scss']
})
export class ProximasTutoriasComponent implements OnInit {

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

    this.tutoriaService.getProximasTutorias(usuarioLogueado).subscribe(proximasTutorias => {
      this.tutorias = proximasTutorias;
    });
  }

}
