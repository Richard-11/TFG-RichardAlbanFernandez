import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatosProfesorComponent } from './datos-profesor.component';

describe('DatosProfesorComponent', () => {
  let component: DatosProfesorComponent;
  let fixture: ComponentFixture<DatosProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatosProfesorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DatosProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
