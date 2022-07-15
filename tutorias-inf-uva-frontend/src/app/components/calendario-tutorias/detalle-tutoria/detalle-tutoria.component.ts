import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Alumno } from 'src/app/models/alumno';
import { Profesor } from 'src/app/models/profesor';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';
import { Tutoria } from 'src/app/models/tutoria';
import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-detalle-tutoria',
  templateUrl: './detalle-tutoria.component.html',
  styleUrls: ['./detalle-tutoria.component.scss']
})
export class DetalleTutoriaComponent implements OnInit {

  tutoria!: Tutoria;
  solicitudTutoria!: SolicitudTutoria;

  constructor(
    private sesionService: SesionService,
    private router: Router,
    private dialogRef: DynamicDialogRef,
    private dialogConfig: DynamicDialogConfig
  ) { }

  ngOnInit(): void {
    if (this.dialogConfig.data) {
      this.tutoria = this.dialogConfig.data.tutoria;
      this.solicitudTutoria = this.tutoria.solicitudTutoria!;
    }
  }

  verTutoria(): void {
    this.dialogRef.close();
    this.router.navigate(['/tutorias', this.tutoria.id]);
  }

  isTutoriaDeUsuario(): boolean {
    const usuarioLogueado = this.sesionService.getUsuarioLogueado();
    if (usuarioLogueado instanceof Profesor) {
      return usuarioLogueado.identificador === this.solicitudTutoria.profesor?.identificador;
    }

    if (usuarioLogueado instanceof Alumno) {
      return usuarioLogueado.identificador === this.solicitudTutoria.alumno?.identificador;
    }

    return false;
  }
}
