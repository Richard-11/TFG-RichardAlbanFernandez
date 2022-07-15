import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Asignatura } from '../models/asignatura';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class AsignaturaService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;

  constructor(
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  public getAsignaturas(codigoTitulacion: number, cursoId?: number, mencionId?: number, nombre?: string): Observable<Asignatura[]> {
    let params = new HttpParams();

    if (codigoTitulacion) {
      params = params.append('codigoTitulacion', codigoTitulacion);
    }

    if (cursoId) {
      params = params.append('cursoId', cursoId);
    }

    if (mencionId) {
      params = params.append('mencionId', mencionId);
    }

    if (nombre) {
      params = params.append('nombre', nombre);
    }

    let options = { params: params };
    let asignaturasUrl = `${this.tutoriasInfUVaApiUrl}/asignaturas`;
    return this.http.get<Asignatura[]>(asignaturasUrl, options).pipe(
      catchError(this.errorService.handleError<Asignatura[]>([]))
    );
  }

  public getAsignaturasUsuario(identificadorUsuario: string): Observable<Asignatura[]> {
    let asignaturasUsuarioUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${identificadorUsuario}/asignaturas`;
    return this.http.get<Asignatura[]>(asignaturasUsuarioUrl).pipe(
      catchError(this.errorService.handleError<Asignatura[]>([]))
    );
  }

  public getAsignaturasEnComun(lista1: Asignatura[], lista2: Asignatura[]): Asignatura[] {
    let asignaturasComun: Asignatura[] = [];

    for (const asiganturaLista1 of lista1) {
      for (const asignaturaLista2 of lista2) {
        if (asiganturaLista1.codigo === asignaturaLista2.codigo) {
          asignaturasComun.push({ ...asiganturaLista1 });
        }
      }
    }

    return asignaturasComun;
  }

}
