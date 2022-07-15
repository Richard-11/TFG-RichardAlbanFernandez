import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HorarioTutorias } from '../models/horario-tutorias';
import { Profesor } from '../models/profesor';
import { SolicitudTutoria } from '../models/solicitud-tutoria';
import { MOMENT_DATETIME_FORMAT, MOMENT_DATE_FORMAT } from '../utils/constantes.utils';
import { DiaUtils } from '../utils/dia.utils';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class HorarioTutoriasService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    params: new HttpParams()
  };

  constructor(
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  public getHorariosTutorias(profesor: Profesor): Observable<HorarioTutorias[]> {
    let horariosUrl = `${this.tutoriasInfUVaApiUrl}/profesores/${profesor.identificador}/horarios`;
    return this.http.get<HorarioTutorias[]>(horariosUrl, this.httpOptions).pipe(
      catchError(this.errorService.handleError<HorarioTutorias[]>())
    );
  }
  public nuevosHorariosTutorias(profesor: Profesor, horarios: HorarioTutorias[]): Observable<HorarioTutorias[]> {
    let horariosUrl = `${this.tutoriasInfUVaApiUrl}/profesores/${profesor.identificador}/horarios`;
    return this.http.post<HorarioTutorias[]>(horariosUrl, horarios, this.httpOptions).pipe(
      catchError(this.errorService.handleError<HorarioTutorias[]>())
    );
  }

  public obtenerHorarioTutoriasActual(horariosTutorias: HorarioTutorias[]): HorarioTutorias | null {
    if (horariosTutorias.length === 1) {
      return horariosTutorias[0];
    } else if (horariosTutorias.length === 2) {
      for (const horario of horariosTutorias) {
        let fechaActual = moment(new Date());
        let fechaInicioHorario = moment(new Date(horario.tipoHorario?.fechaInicio!));
        let fechaFinHorario = moment(new Date(horario.tipoHorario?.fechaFin!));

        if (fechaActual.isBetween(fechaInicioHorario, fechaFinHorario, undefined, '[]')) {
          return horario;
        }
      }
    }

    return null;
  }

  public isSolicitudDentroHorarioTutoriasProfesor(solicitud: SolicitudTutoria, horariosTutoriasProfesor: HorarioTutorias[]): boolean {
    if (!horariosTutoriasProfesor) {
      return false;
    }

    let horarioTutoriasActual = this.obtenerHorarioTutoriasActual(horariosTutoriasProfesor);

    if (horarioTutoriasActual != null) {
      const diaTutoriaSolicitud = moment(solicitud.fechaTutoria as string, MOMENT_DATE_FORMAT).toDate().getDay();

      const fechaInicioTutoriaSolicitud = moment(this.addStringDateAndTimeToDate(solicitud.fechaTutoria as string, solicitud.horaInicio as string));
      const fechaFinTutoriaSolicitud = moment(this.addStringDateAndTimeToDate(solicitud.fechaTutoria as string, solicitud.horaFin as string))

      for (const franja of horarioTutoriasActual.franjasTutorias!) {
        if (diaTutoriaSolicitud === DiaUtils.getNumeroDia(franja.dia!)) {
          const horaInicio = moment(this.addStringDateAndTimeToDate(solicitud.fechaTutoria as string, franja.horaInicio as string));
          const horaFin = moment(this.addStringDateAndTimeToDate(solicitud.fechaTutoria as string, franja.horaFin as string));

          if (fechaInicioTutoriaSolicitud.isBetween(horaInicio, horaFin, undefined, '[]') && fechaFinTutoriaSolicitud.isBetween(horaInicio, horaFin, undefined, '[]')) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private addStringDateAndTimeToDate(stringDate: string, stringTime: string): Date {
    return moment(`${stringDate} ${stringTime}`, MOMENT_DATETIME_FORMAT).toDate();
  }
}
