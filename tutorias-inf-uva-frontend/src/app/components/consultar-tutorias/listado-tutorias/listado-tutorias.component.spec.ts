import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoTutoriasComponent } from './listado-tutorias.component';

describe('ListadoTutoriasComponent', () => {
  let component: ListadoTutoriasComponent;
  let fixture: ComponentFixture<ListadoTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListadoTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
