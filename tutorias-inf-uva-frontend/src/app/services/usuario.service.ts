import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario';
import { ErrorService } from './error.service';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private tutoriasInfUVaApiUrl = environment.tutoriasApiUrl;
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    params: new HttpParams()
  };

  constructor(
    private http: HttpClient,
    private errorService: ErrorService
  ) { }

  public login(identificador: string, password: string): Observable<any> {
    let options = { ...this.httpOptions };
    options.headers = options.headers.append('authorization', password);

    const usuarioUrl = `${this.tutoriasInfUVaApiUrl}/login/${identificador}`;
    return this.http.post<Usuario>(usuarioUrl, {}, options).pipe(
      catchError(this.errorService.handleError<Usuario>())
    );
  }

}
