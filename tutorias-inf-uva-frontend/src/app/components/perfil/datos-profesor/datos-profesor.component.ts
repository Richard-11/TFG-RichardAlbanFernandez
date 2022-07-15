import { Component, Input, OnInit } from '@angular/core';
import { Profesor } from 'src/app/models/profesor';

@Component({
  selector: 'app-datos-profesor',
  templateUrl: './datos-profesor.component.html',
  styleUrls: ['./datos-profesor.component.scss']
})
export class DatosProfesorComponent implements OnInit {

  @Input() profesor!: Profesor;

  constructor() { }

  ngOnInit(): void {
  }

}
