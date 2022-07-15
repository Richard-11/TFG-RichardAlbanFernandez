import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitarTutoriaComponent } from './solicitar-tutoria.component';

describe('SolicitarTutoriaComponent', () => {
  let component: SolicitarTutoriaComponent;
  let fixture: ComponentFixture<SolicitarTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolicitarTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitarTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
