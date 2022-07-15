import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudTutoriaComponent } from './solicitud-tutoria.component';

describe('SolicitudTutoriaComponent', () => {
  let component: SolicitudTutoriaComponent;
  let fixture: ComponentFixture<SolicitudTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitudTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitudTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
