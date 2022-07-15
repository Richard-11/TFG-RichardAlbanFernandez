import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { MessageService } from 'src/app/services/message.service';
import { SesionService } from 'src/app/services/sesion.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  readonly cerrarSesionKey = 'cerrarSesionKey';

  constructor(
    private sesionService: SesionService,
    private messageService: MessageService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  obtenerTipoUsuarioYNombreCompleto(): string {
    const usuario = this.sesionService.getUsuarioLogueado();
    const tipoUsuario = usuario instanceof Alumno ? 'Alumno' : 'Profesor';

    return `${tipoUsuario}: ${usuario!.nombre} ${usuario!.apellidos}`
  }

  async cerrarSesion(): Promise<void> {
    if (await this.confirmCerrarSesion()) {
      this.sesionService.terminarSesion();
      this.router.navigate(['/login']);
    }
  }

  private confirmCerrarSesion(): Promise<boolean> {
    const message = 'Vas a terminar la sesión, ¿quieres continuar?';
    return this.messageService.showConfirmDialog(message, this.cerrarSesionKey);
  }
}
