import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class ErrorService {

  constructor(private messageService: MessageService) { }

  public handleError<T>(result?: T): (error: HttpErrorResponse) => Observable<T> {
    return (error: HttpErrorResponse): Observable<T> => {
      let message = error.error;
      if (error.status === 0) {
        message = 'Servidor no disponible';
      } else if (error.status >= HttpStatusCode.InternalServerError) {
        message = 'Error en el servidor. Póngase en contacto con los técnicos.'
      }

      this.messageService.showErrorMessage(message);
      
      return of(result as T);
    }
  }
}
