import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TutoriasProgramadasComponent } from './tutorias-programadas.component';

describe('TutoriasProgramadasComponent', () => {
  let component: TutoriasProgramadasComponent;
  let fixture: ComponentFixture<TutoriasProgramadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TutoriasProgramadasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TutoriasProgramadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
