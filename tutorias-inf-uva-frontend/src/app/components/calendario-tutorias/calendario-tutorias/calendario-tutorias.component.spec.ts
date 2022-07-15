import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarioTutoriasComponent } from './calendario-tutorias.component';

describe('CalendarioTutoriasComponent', () => {
  let component: CalendarioTutoriasComponent;
  let fixture: ComponentFixture<CalendarioTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CalendarioTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CalendarioTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
