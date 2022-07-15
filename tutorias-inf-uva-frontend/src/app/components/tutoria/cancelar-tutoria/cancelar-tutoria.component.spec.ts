import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelarTutoriaComponent } from './cancelar-tutoria.component';

describe('CancelarTutoriaComponent', () => {
  let component: CancelarTutoriaComponent;
  let fixture: ComponentFixture<CancelarTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelarTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelarTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
