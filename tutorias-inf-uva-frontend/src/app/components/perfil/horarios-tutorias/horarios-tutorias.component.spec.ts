import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorariosTutoriasComponent } from './horarios-tutorias.component';

describe('HorariosTutoriasComponent', () => {
  let component: HorariosTutoriasComponent;
  let fixture: ComponentFixture<HorariosTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HorariosTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HorariosTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
