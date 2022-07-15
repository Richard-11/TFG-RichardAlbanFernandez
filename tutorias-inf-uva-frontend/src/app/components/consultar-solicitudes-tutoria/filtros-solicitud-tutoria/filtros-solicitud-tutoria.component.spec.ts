import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrosSolicitudTutoriaComponent } from './filtros-solicitud-tutoria.component';

describe('FiltrosSolicitudTutoriaComponent', () => {
  let component: FiltrosSolicitudTutoriaComponent;
  let fixture: ComponentFixture<FiltrosSolicitudTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FiltrosSolicitudTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltrosSolicitudTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
