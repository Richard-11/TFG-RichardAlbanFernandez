import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';

@Component({
  selector: 'app-solicitudes-inicio',
  templateUrl: './solicitudes-inicio.component.html',
  styleUrls: ['./solicitudes-inicio.component.scss']
})
export class SolicitudesInicioComponent implements OnInit {

  @Input() solicitudes: SolicitudTutoria[] = [];
  @Input() title = '';
  @Input() responsiveOptions: any;
  @Input() isProfesor = false;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  verSolicitudTutoria(solicitud: SolicitudTutoria): void {
    this.router.navigate(['/solicitudes', solicitud.id]);
  }

  obtenerColorBoton(): string {
    return this.isProfesor ? 'p-button-warning p-button-rounded' : 'p-button-success p-button-rounded';
  }

}
