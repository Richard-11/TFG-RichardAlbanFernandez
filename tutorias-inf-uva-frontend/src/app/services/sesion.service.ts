import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { Profesor } from '../models/profesor';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class SesionService {
  private usuarioLogueado: Usuario | null = null;
  private readonly usuarioLogueadoKey = 'usuarioLogueado';
  private readonly tipoUsuarioKey = 'tipoUsuario';

  constructor() {
    this.mirarEnLocalStorage();
  }

  setUsuarioLogeado(usuario: Usuario): void {
    this.usuarioLogueado = usuario;
    this.guardarEnLocalStorage();
  }

  getUsuarioLogueado() {
    return this.usuarioLogueado;
  }

  isSesionActiva(): boolean {
    if (this.usuarioLogueado) {
      return true;
    }

    return false;
  }

  terminarSesion() {
    this.usuarioLogueado = null;
    localStorage.clear();
  }

  private mirarEnLocalStorage(): void {
    const usuarioJSON = localStorage.getItem(this.usuarioLogueadoKey);

    if (usuarioJSON != null) {
      const tipo = localStorage.getItem(this.tipoUsuarioKey);

      if (tipo != null) {
        switch (tipo) {
          case 'Alumno':
            this.usuarioLogueado = new Alumno(JSON.parse(usuarioJSON));
            break;
          case 'Profesor':
            this.usuarioLogueado = new Profesor(JSON.parse(usuarioJSON));
            break;
        }
      }
    }
  }

  private guardarEnLocalStorage(): void {
    if (this.usuarioLogueado) {
      const usuarioJson = JSON.stringify(this.usuarioLogueado);

      let tipoUsuario;
      if (this.usuarioLogueado instanceof Alumno) {
        tipoUsuario = 'Alumno';
      } else {
        tipoUsuario = 'Profesor';
      }

      localStorage.setItem(this.usuarioLogueadoKey, usuarioJson);
      localStorage.setItem(this.tipoUsuarioKey, tipoUsuario);
    }
  }
}