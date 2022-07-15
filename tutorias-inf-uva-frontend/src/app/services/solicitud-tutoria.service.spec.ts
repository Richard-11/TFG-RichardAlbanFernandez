import { TestBed } from '@angular/core/testing';

import { SolicitudTutoriaService } from './solicitud-tutoria.service';

describe('SolicitudTutoriaService', () => {
  let service: SolicitudTutoriaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudTutoriaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
