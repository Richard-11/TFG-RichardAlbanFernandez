import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TutoriasHoyComponent } from './tutorias-hoy.component';

describe('TutoriasHoyComponent', () => {
  let component: TutoriasHoyComponent;
  let fixture: ComponentFixture<TutoriasHoyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TutoriasHoyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TutoriasHoyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
