import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EstadoSolicitudEnum } from '../models/enums/estado-solicitud-enum';
import { FiltrosTutoria } from '../models/filtros-tutoria';
import { SolicitudTutoria } from '../models/solicitud-tutoria';
import { Usuario } from '../models/usuario';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class SolicitudTutoriaService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    params: new HttpParams()
  };

  constructor(
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  public getSolicitudTutoria(usuario: Usuario, solicitudId: number): Observable<SolicitudTutoria> {
    const solicitudTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/solicitudes/${solicitudId}`;
    return this.http.get<SolicitudTutoria>(solicitudTutoriaUrl, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  public getSolicitudesTutoria(usuario: Usuario, filtros: FiltrosTutoria): Observable<SolicitudTutoria[]> {
    const params = this.construirParametros(filtros);
    let httpOptions = { ...this.httpOptions }
    httpOptions.params = params;

    const solicitudesTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/usuarios/${usuario.identificador}/solicitudes`;
    return this.http.get<SolicitudTutoria[]>(solicitudesTutoriaUrl, httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria[]>([]))
    );
  }

  public nuevaSolicitudTutoria(nuevaSolicitud: SolicitudTutoria): Observable<SolicitudTutoria> {
    let solicitudesTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/solicitudes`;
    return this.http.post<SolicitudTutoria>(solicitudesTutoriaUrl, nuevaSolicitud, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  public cancelarSolicitudTutoria(solicitudTutoria: SolicitudTutoria): Observable<SolicitudTutoria> {
    let solicitud: SolicitudTutoria = { ...solicitudTutoria };
    solicitud.estadoSolicitud = EstadoSolicitudEnum.CANCELADA;
    const solicitudTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/solicitudes/${solicitudTutoria.id}`;
    return this.http.put<SolicitudTutoria>(solicitudTutoriaUrl, solicitud, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  public aceptarSolicitudTutoria(solicitudTutoria: SolicitudTutoria): Observable<SolicitudTutoria> {
    let solicitud: SolicitudTutoria = { ...solicitudTutoria };
    solicitud.estadoSolicitud = EstadoSolicitudEnum.ACEPTADA;
    const solicitudTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/solicitudes/${solicitudTutoria.id}`;
    return this.http.put<SolicitudTutoria>(solicitudTutoriaUrl, solicitud, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  public rechazarSolicitudTutoria(solicitudTutoria: SolicitudTutoria): Observable<SolicitudTutoria> {
    let solicitud: SolicitudTutoria = { ...solicitudTutoria };
    solicitud.estadoSolicitud = EstadoSolicitudEnum.RECHAZADA;
    const solicitudTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/solicitudes/${solicitudTutoria.id}`;
    return this.http.put<SolicitudTutoria>(solicitudTutoriaUrl, solicitud, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  public confirmarSolicitudTutoria(solicitudTutoria: SolicitudTutoria): Observable<SolicitudTutoria> {
    let solicitud: SolicitudTutoria = { ...solicitudTutoria };
    solicitud.estadoSolicitud = EstadoSolicitudEnum.CONFIRMADA;
    const solicitudTutoriaUrl = `${this.tutoriasInfUVaApiUrl}/solicitudes/${solicitudTutoria.id}`;
    return this.http.put<SolicitudTutoria>(solicitudTutoriaUrl, solicitud, this.httpOptions).pipe(
      catchError(this.errorService.handleError<SolicitudTutoria>())
    );
  }

  private construirParametros(filtros: FiltrosTutoria): HttpParams {
    let params = new HttpParams();

    if (filtros.estadoSolicitud) {
      params = params.append('estadoSolicitudId', filtros.estadoSolicitud.id);
    }

    if (filtros.fechaTutoria) {
      params = params.append('fechaTutoria', filtros.fechaTutoria);
    }

    if (filtros.horaInicio) {
      params = params.append('horaInicio', filtros.horaInicio);
    }

    if (filtros.horaFin) {
      params = params.append('horaFin', filtros.horaFin);
    }

    if (filtros.grupal != null) {
      params = params.append('grupal', filtros.grupal);
    }

    if (filtros.profesor) {
      params = params.append('profesorId', filtros.profesor.identificador);
    }

    if (filtros.asignatura) {
      params = params.append('codigoAsignatura', filtros.asignatura.codigo!);
    }

    return params;
  }
}