import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Profesor } from '../models/profesor';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;

  constructor(
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  public getProfesor(identificador: string): Observable<Profesor> {
    let profesorUrl = `${this.tutoriasInfUVaApiUrl}/profesores/${identificador}`;
    return this.http.get<Profesor>(profesorUrl).pipe(
      catchError(this.errorService.handleError<Profesor>())
    )
  }

  public getProfesores(nombre?: string): Observable<Profesor[]> {
    let params = nombre ? new HttpParams().append('nombre', nombre) : {};
    let options = { params: params };

    let profesoresUrl = `${this.tutoriasInfUVaApiUrl}/profesores`;
    return this.http.get<Profesor[]>(profesoresUrl, options).pipe(
      catchError(this.errorService.handleError<Profesor[]>([]))
    );
  }

  public getProfesoresAsignatura(codigoAsignatura: number): Observable<Profesor[]> {
    let profesoresAsignaturaUrl = `${this.tutoriasInfUVaApiUrl}/asignaturas/${codigoAsignatura}/profesores`;
    return this.http.get<Profesor[]>(profesoresAsignaturaUrl).pipe(
      catchError(this.errorService.handleError<Profesor[]>())
    );
  }

  public getProfesoresTitulacion(codigoTitulacion: number) {
    let profesoresTitulacionUrl = `${this.tutoriasInfUVaApiUrl}/titulaciones/${codigoTitulacion}/profesores`;
    return this.http.get<Profesor[]>(profesoresTitulacionUrl).pipe(
      catchError(this.errorService.handleError<Profesor[]>())
    );
  }

}
