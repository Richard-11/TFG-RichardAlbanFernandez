import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { SesionService } from './services/sesion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(
    private sesionService: SesionService,
    private primeNGConfig: PrimeNGConfig, 
    private translateService: TranslateService
  ) {}

  ngOnInit(): void {
    this.translateService.setDefaultLang('es');
    this.translateService.get('primeng').subscribe(res => this.primeNGConfig.setTranslation(res));
  }

  isSesionActiva(): boolean {
    return this.sesionService.isSesionActiva();
  }
}
