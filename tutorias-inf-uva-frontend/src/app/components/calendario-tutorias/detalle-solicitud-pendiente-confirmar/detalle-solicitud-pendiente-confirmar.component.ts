import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { SolicitudTutoria } from 'src/app/models/solicitud-tutoria';

@Component({
  selector: 'app-detalle-solicitud-pendiente-confirmar',
  templateUrl: './detalle-solicitud-pendiente-confirmar.component.html',
  styleUrls: ['./detalle-solicitud-pendiente-confirmar.component.scss']
})
export class DetalleSolicitudPendienteConfirmarComponent implements OnInit {

  solicitudTutoria!: SolicitudTutoria;

  constructor(
    private router: Router,
    private dialogRef: DynamicDialogRef,
    private dialogConfig: DynamicDialogConfig
  ) { }

  ngOnInit(): void {
    if (this.dialogConfig.data) {
      this.solicitudTutoria = this.dialogConfig.data.solicitud;
    }
  }

  verSolicitudTutoria(): void {
    this.dialogRef.close();
    this.router.navigate(['/solicitudes', this.solicitudTutoria.id]);
  }
}
