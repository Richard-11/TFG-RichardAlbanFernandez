import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tutoria } from 'src/app/models/tutoria';

@Component({
  selector: 'app-tutorias-inicio',
  templateUrl: './tutorias-inicio.component.html',
  styleUrls: ['./tutorias-inicio.component.scss']
})
export class TutoriasInicioComponent implements OnInit {

  @Input() title = '';
  @Input() tutorias: Tutoria[] = [];
  @Input() responsiveOptions: any;
  @Input() isProfesor = false;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  verTutoria(tutoria: Tutoria): void {
    this.router.navigate(['/tutorias', tutoria.id]);
  }
}
