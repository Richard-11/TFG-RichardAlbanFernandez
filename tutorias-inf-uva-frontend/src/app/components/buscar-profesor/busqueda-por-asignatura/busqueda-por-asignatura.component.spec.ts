import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaPorAsignaturaComponent } from './busqueda-por-asignatura.component';

describe('BusquedaPorAsignaturaComponent', () => {
  let component: BusquedaPorAsignaturaComponent;
  let fixture: ComponentFixture<BusquedaPorAsignaturaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusquedaPorAsignaturaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusquedaPorAsignaturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
