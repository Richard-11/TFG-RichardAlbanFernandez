import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProximasTutoriasComponent } from './proximas-tutorias.component';

describe('ProximasTutoriasComponent', () => {
  let component: ProximasTutoriasComponent;
  let fixture: ComponentFixture<ProximasTutoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProximasTutoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProximasTutoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
