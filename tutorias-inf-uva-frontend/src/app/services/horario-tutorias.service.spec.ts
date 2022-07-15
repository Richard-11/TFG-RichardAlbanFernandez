import { TestBed } from '@angular/core/testing';

import { HorarioTutoriasService } from './horario-tutorias.service';

describe('HorarioTutoriasService', () => {
  let service: HorarioTutoriasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HorarioTutoriasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
