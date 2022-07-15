import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaPorProfesorComponent } from './busqueda-por-profesor.component';

describe('BusquedaPorProfesorComponent', () => {
  let component: BusquedaPorProfesorComponent;
  let fixture: ComponentFixture<BusquedaPorProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BusquedaPorProfesorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BusquedaPorProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
