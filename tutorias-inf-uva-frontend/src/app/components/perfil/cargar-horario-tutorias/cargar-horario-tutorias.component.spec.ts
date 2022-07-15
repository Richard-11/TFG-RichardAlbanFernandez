import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargarHorarioTutoriasComponent } from './cargar-horario-tutorias.component';

describe('CargarHorarioTutoriasComponent', () => {
  let component: CargarHorarioTutoriasComponent;
  let fixture: ComponentFixture<CargarHorarioTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CargarHorarioTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CargarHorarioTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
