import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AceptarSolicitudTutoriaComponent } from './aceptar-solicitud-tutoria.component';

describe('AceptarSolicitudTutoriaComponent', () => {
  let component: AceptarSolicitudTutoriaComponent;
  let fixture: ComponentFixture<AceptarSolicitudTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AceptarSolicitudTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AceptarSolicitudTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
