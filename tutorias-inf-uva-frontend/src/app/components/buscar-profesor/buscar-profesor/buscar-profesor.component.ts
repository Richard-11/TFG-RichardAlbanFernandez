import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-buscar-profesor',
  templateUrl: './buscar-profesor.component.html',
  styleUrls: ['./buscar-profesor.component.scss']
})
export class BuscarProfesorComponent implements OnInit {

  items!: MenuItem[];
  activeItem!: MenuItem;

  constructor() { }

  ngOnInit(): void {
    this.items = [
      {label: 'Profesor', routerLink: 'busqueda-por-profesor'},
      {label: 'Asignatura', routerLink: 'busqueda-por-asignatura'}
    ];

    this.activeItem = this.items[0];
  }
}
