import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleSolicitudPendienteConfirmarComponent } from './detalle-solicitud-pendiente-confirmar.component';

describe('DetalleSolicitudPendienteConfirmarComponent', () => {
  let component: DetalleSolicitudPendienteConfirmarComponent;
  let fixture: ComponentFixture<DetalleSolicitudPendienteConfirmarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleSolicitudPendienteConfirmarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleSolicitudPendienteConfirmarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
