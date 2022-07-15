import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { Alumno } from 'src/app/models/alumno';
import { EstadoSolicitudEnum } from 'src/app/models/enums/estado-solicitud-enum';
import { EstadoSolicitud } from 'src/app/models/estado-solicitud';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { Usuario } from 'src/app/models/usuario';
import { SesionService } from 'src/app/services/sesion.service';
import { SolicitudTutoriaService } from 'src/app/services/solicitud-tutoria.service';
import { TutoriaService } from 'src/app/services/tutoria.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {
  private usuarioLogueado: Usuario | null = null;

  proximasTutorias: Tutoria[] = [];
  solicitudes: SolicitudTutoria[] = [];
  solicitudesTitle = '';

  responsiveOptions = [
    {
      breakpoint: '1024px',
      numVisible: 3,
      numScroll: 3
    },
    {
      breakpoint: '768px',
      numVisible: 2,
      numScroll: 2
    },
    {
      breakpoint: '560px',
      numVisible: 1,
      numScroll: 1
    }
  ];

  constructor(
    private solicitudTutoriaService: SolicitudTutoriaService,
    private tutoriaService: TutoriaService,
    private sesionService: SesionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuarioLogueado = this.sesionService.getUsuarioLogueado();

    if (this.usuarioLogueado == null) {
      return;
    }

    let estadoSolicitud: EstadoSolicitud | null = null;
    if (this.isProfesor()) {
      this.solicitudesTitle = 'Solicitudes pendientes';
      estadoSolicitud = EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.PENDIENTE);
    }

    if (this.isAlumno()) {
      this.solicitudesTitle = 'Solicitudes aceptadas';
      estadoSolicitud = EstadoSolicitud.getEstadoSolicitudByEnum(EstadoSolicitudEnum.ACEPTADA);
    }

    if (estadoSolicitud == null) {
      return;
    }

    const solicitudes = this.solicitudTutoriaService.getSolicitudesTutoria(this.usuarioLogueado, { estadoSolicitud: estadoSolicitud });
    const tutorias = this.tutoriaService.getProximasTutorias(this.usuarioLogueado);

    forkJoin([solicitudes, tutorias]).subscribe(results => {
      if (results) {
        this.solicitudes = results[0];
        this.proximasTutorias = results[1];
      }
    });
  }

  verSolicitudTutoria(solicitud: SolicitudTutoria): void {
    this.router.navigate(['/solicitudes', solicitud.id]);
  }

  isProfesor(): boolean {
    return this.usuarioLogueado instanceof Profesor;
  }

  isAlumno(): boolean {
    return this.usuarioLogueado instanceof Alumno;
  }
}
