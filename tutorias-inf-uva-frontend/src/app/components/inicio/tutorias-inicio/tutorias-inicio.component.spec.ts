import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TutoriasInicioComponent } from './tutorias-inicio.component';

describe('TutoriasInicioComponent', () => {
  let component: TutoriasInicioComponent;
  let fixture: ComponentFixture<TutoriasInicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TutoriasInicioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TutoriasInicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
