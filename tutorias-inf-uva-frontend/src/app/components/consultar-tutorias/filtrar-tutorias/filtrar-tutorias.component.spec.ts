import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrarTutoriasComponent } from './filtrar-tutorias.component';

describe('FiltrarTutoriasComponent', () => {
  let component: FiltrarTutoriasComponent;
  let fixture: ComponentFixture<FiltrarTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FiltrarTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FiltrarTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
