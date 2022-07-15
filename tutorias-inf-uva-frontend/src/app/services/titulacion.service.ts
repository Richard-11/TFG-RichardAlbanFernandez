import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Titulacion } from '../models/titulacion';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class TitulacionService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;

  constructor(
    private http: HttpClient, 
    private errorService: ErrorService
  ) { }

  public getTitulaciones(): Observable<Titulacion[]> {
    let titulacionesUrl = `${this.tutoriasInfUVaApiUrl}/titulaciones`;
    return this.http.get<Titulacion[]>(titulacionesUrl).pipe(
      catchError(this.errorService.handleError<Titulacion[]>([]))
    );
  }

}