import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-consultar-tutorias',
  templateUrl: './consultar-tutorias.component.html',
  styleUrls: ['./consultar-tutorias.component.scss']
})
export class ConsultarTutoriasComponent implements OnInit {

  opciones = [
    { tipo: 1, label: 'Próximamente' },
    { tipo: 2, label: 'Hoy' },
    { tipo: 3, label: 'Filtrar' },
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onChange(selectedValue: any): void {
    let childrenPath: string;
    
    switch(selectedValue.tipo) {
      case(2):
        childrenPath = 'hoy';
        break;
      case(3):
        childrenPath = 'filtrar';
        break;
      default:
        childrenPath = 'proximamente';
    }

    this.router.navigate([childrenPath], { relativeTo: this.route });
  }
}
