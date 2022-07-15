import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarTutoriasComponent } from './consultar-tutorias.component';

describe('ConsultarTutoriasComponent', () => {
  let component: ConsultarTutoriasComponent;
  let fixture: ComponentFixture<ConsultarTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
