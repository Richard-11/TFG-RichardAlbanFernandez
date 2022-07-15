import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { Profesor } from 'src/app/models/profesor';
import { ProfesorService } from 'src/app/services/profesor.service';

@Component({
  selector: 'app-informacion-profesor',
  templateUrl: './informacion-profesor.component.html',
  styleUrls: ['./informacion-profesor.component.scss']
})
export class InformacionProfesorComponent implements OnInit {

  profesor!: Profesor;

  items!: MenuItem[];
  activeIndex!: number; 

  constructor(
    private profesorService: ProfesorService, 
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.items = [
      {label: 'Asignaturas', routerLink: 'asignaturas'},
      {label: 'Tutorías programadas', routerLink: 'tutorias-programadas'}
    ]

    const identificadorProfesor = this.route.snapshot.paramMap.get('identificador');

    this.profesorService.getProfesor(identificadorProfesor!).subscribe(profesor => {
      this.profesor = new Profesor(profesor);
    });
  }

}
