import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrosTutoriaComponent } from './filtros-tutoria.component';

describe('FiltrosTutoriaComponent', () => {
  let component: FiltrosTutoriaComponent;
  let fixture: ComponentFixture<FiltrosTutoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FiltrosTutoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltrosTutoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
