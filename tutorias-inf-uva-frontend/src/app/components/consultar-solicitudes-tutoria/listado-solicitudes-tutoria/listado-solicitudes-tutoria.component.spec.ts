import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoSolicitudesTutoriaComponent } from './listado-solicitudes-tutoria.component';

describe('ListadoSolicitudesTutoriaComponent', () => {
  let component: ListadoSolicitudesTutoriaComponent;
  let fixture: ComponentFixture<ListadoSolicitudesTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListadoSolicitudesTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoSolicitudesTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
