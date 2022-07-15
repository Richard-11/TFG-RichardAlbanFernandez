import { Component, Input, OnInit } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';

@Component({
  selector: 'app-datos-alumno',
  templateUrl: './datos-alumno.component.html',
  styleUrls: ['./datos-alumno.component.scss']
})
export class DatosAlumnoComponent implements OnInit {

  @Input() alumno!: Alumno;

  constructor() { }

  ngOnInit(): void {
  }

}
