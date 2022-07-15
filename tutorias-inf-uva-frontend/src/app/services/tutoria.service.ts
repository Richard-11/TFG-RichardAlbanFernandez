import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { FiltrosTutoria } from '../models/filtros-tutoria';
import { Tutoria } from '../models/tutoria';
import { Usuario } from '../models/usuario';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export abstract class TutoriaService {

  protected tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;
  protected httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    params: new HttpParams()
  };

  constructor(
    private http: HttpClient,
    private errorService: ErrorService,
  ) { }

  getTutoria(usuario: Usuario, tutoriaId: number): Observable<Tutoria> {
    const tutoriaUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/tutorias/${tutoriaId}`;
    return this.http.get<Tutoria>(tutoriaUrl, this.httpOptions).pipe(
      catchError(this.errorService.handleError<Tutoria>())
    );
  }

  getProximasTutorias(usuario: Usuario): Observable<Tutoria[]> {
    let params = new HttpParams();
    params = params.append('proximas', true);
    let httpOptions = { ...this.httpOptions }
    httpOptions.params = params;

    const tutoriasUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/tutorias`;
    return this.http.get<Tutoria[]>(tutoriasUrl, httpOptions).pipe(
      catchError(this.errorService.handleError<Tutoria[]>([]))
    );
  }

  getTutoriasHoy(usuario: Usuario): Observable<Tutoria[]> {
    let params = new HttpParams();
    params = params.append('hoy', true);
    let httpOptions = { ...this.httpOptions }
    httpOptions.params = params;

    const tutoriasUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/tutorias`;
    return this.http.get<Tutoria[]>(tutoriasUrl, httpOptions).pipe(
      catchError(this.errorService.handleError<Tutoria[]>([]))
    );
  }

  getTutorias(usuario: Usuario, filtros: FiltrosTutoria): Observable<Tutoria[]> {
    const params = this.construirParametros(filtros);
    let httpOptions = { ...this.httpOptions }
    httpOptions.params = params;

    const tutoriasUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/tutorias`;
    return this.http.get<Tutoria[]>(tutoriasUrl, httpOptions).pipe(
      catchError(this.errorService.handleError<Tutoria[]>([]))
    );
  }

  public cancelarTutoria(tutoria: Tutoria): Observable<Tutoria> {
    let tutoriaACancelar: Tutoria = { ...tutoria };
    const tutoriaUrl = `${this.tutoriasInfUVaApiUrl}/tutorias/${tutoria.id}`;
    return this.http.put<Tutoria>(tutoriaUrl, tutoriaACancelar, this.httpOptions).pipe(
      catchError(this.errorService.handleError<Tutoria>())
    );
  }

  protected construirParametros(filtros: FiltrosTutoria): HttpParams {
    let params = new HttpParams();

    if (filtros.fechaTutoria) {
      params = params.append('fechaTutoria', filtros.fechaTutoria);
    }

    if (filtros.horaInicio) {
      params = params.append('horaInicio', filtros.horaInicio);
    }

    if (filtros.horaFin) {
      params = params.append('horaFin', filtros.horaFin);
    }

    if (filtros.cancelada != null) {
      params = params.append('cancelada', filtros.cancelada);
    }

    if (filtros.grupal != null) {
      params = params.append('grupal', filtros.grupal);
    }

    if (filtros.profesor) {
      params = params.append('profesorId', filtros.profesor.identificador);
    }

    if (filtros.asignatura && filtros.asignatura.codigo) {
      params = params.append('codigoAsignatura', filtros.asignatura.codigo);
    }

    return params;
  }
}