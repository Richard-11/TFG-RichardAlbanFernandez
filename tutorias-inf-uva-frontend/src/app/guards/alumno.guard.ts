import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Alumno } from '../models/alumno';
import { SesionService } from '../services/sesion.service';

@Injectable({
  providedIn: 'root'
})
export class AlumnoGuard implements CanActivate {

  constructor(
    private sesionService: SesionService,
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const usuarioLogueado = this.sesionService.getUsuarioLogueado();

    if (usuarioLogueado == null || !(usuarioLogueado instanceof Alumno)) {
      return this.router.navigate(['/home']).then(() => false);
    }

    return true;
  }

}
