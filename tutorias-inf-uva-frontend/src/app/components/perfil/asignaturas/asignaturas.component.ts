import { Component, Input, OnInit } from '@angular/core';
import { Asignatura } from 'src/app/models/asignatura';
import { Usuario } from 'src/app/models/usuario';
import { AsignaturaService } from 'src/app/services/asignatura.service';

@Component({
  selector: 'app-asignaturas',
  templateUrl: './asignaturas.component.html',
  styleUrls: ['./asignaturas.component.scss']
})
export class AsignaturasComponent implements OnInit {

  @Input() usuario!: Usuario;
  asignaturas: Asignatura[] = [];

  constructor(
    private asignaturaService: AsignaturaService
  ) { }

  ngOnInit(): void {
    this.asignaturaService.getAsignaturasUsuario(this.usuario.identificador).subscribe(asignaturas => {
      this.asignaturas = asignaturas;
    });
  }

  getTitulacionesAsignatura(asignatura: Asignatura): string {
    let titulaciones = asignatura.cursos?.map(c => c.titulacion);
    let titulacionesNombreYPlan = titulaciones?.map(t => `${t?.nombre} [${t?.plan}]`);
    let unique = Array.from(new Set(titulacionesNombreYPlan));

    return unique?.length ? unique.join(', ') : '';
  }
}
