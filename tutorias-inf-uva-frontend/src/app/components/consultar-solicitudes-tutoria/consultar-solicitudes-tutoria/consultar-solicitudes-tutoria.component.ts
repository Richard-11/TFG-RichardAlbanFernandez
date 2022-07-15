import { Component, OnInit } from '@angular/core';
import { FiltrosTutoria } from 'src/app/models/filtros-tutoria';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';

@Component({
  selector: 'app-consultar-solicitudes-tutoria',
  templateUrl: './consultar-solicitudes-tutoria.component.html',
  styleUrls: ['./consultar-solicitudes-tutoria.component.scss']
})
export class ConsultarSolicitudesTutoriaComponent implements OnInit {

  buscarClicked = false;
  solicitudes: SolicitudTutoria[] = [];

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private sesionService: SesionService
  ) { }

  ngOnInit(): void {
  }

  buscarSolicitudesTutoria(filtros: FiltrosTutoria) {
    this.buscarClicked = true;

    const usuarioLogeado = this.sesionService.getUsuarioLogueado();
    this.solicitudTutoriaService.getSolicitudesTutoria(usuarioLogeado!, filtros).subscribe(solicitudes => {
      this.solicitudes = solicitudes;
    });
  }

}
