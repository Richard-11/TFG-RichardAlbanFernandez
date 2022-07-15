import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechazarSolicitudTutoriaComponent } from './rechazar-solicitud-tutoria.component';

describe('RechazarSolicitudTutoriaComponent', () => {
  let component: RechazarSolicitudTutoriaComponent;
  let fixture: ComponentFixture<RechazarSolicitudTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RechazarSolicitudTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RechazarSolicitudTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
