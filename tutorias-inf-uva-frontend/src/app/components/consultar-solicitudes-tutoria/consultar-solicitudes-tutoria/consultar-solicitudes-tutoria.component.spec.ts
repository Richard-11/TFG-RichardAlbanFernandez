import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarSolicitudesTutoriaComponent } from './consultar-solicitudes-tutoria.component';

describe('ConsultarSolicitudesTutoriaComponent', () => {
  let component: ConsultarSolicitudesTutoriaComponent;
  let fixture: ComponentFixture<ConsultarSolicitudesTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarSolicitudesTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarSolicitudesTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
