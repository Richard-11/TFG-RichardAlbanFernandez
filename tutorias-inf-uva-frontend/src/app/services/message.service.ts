import { Injectable } from '@angular/core';
import { ConfirmationService, MessageService as PrimeNGMessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(
    private primeNGMessageService: PrimeNGMessageService,
    private confirmationService: ConfirmationService
  ) { }

  public showInfoMessage(detail: string, clear: boolean = true): void {
    if (clear) {
      this.primeNGMessageService.clear();
    }

    this.primeNGMessageService.add({
      severity: 'info',
      summary: 'Info',
      detail: detail,
    });
  }

  public showSuccessMessage(detail: string): void {
    this.primeNGMessageService.clear();

    this.primeNGMessageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: detail,
    });
  }

  public showWarningMessage(detail: string, key?: string): void {
    this.primeNGMessageService.clear();

    this.primeNGMessageService.add({
      severity: 'warn',
      summary: 'Advertencia',
      detail: detail,
      key: key
    });
  }

  public showErrorMessage(detail: string): void {
    this.primeNGMessageService.clear();

    this.primeNGMessageService.add({
      severity: 'error',
      summary: 'Error',
      detail: detail,
    });
  }

  public showConfirmDialog(message: string, key: string): Promise<boolean> {
    return new Promise(resolve => {
      this.confirmationService.confirm({
        message: message,
        key: key,
        header: 'Confirmación',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
          resolve(true);
        },
        reject: () => {
          resolve(false);
        }
      });
    });
  }
}
