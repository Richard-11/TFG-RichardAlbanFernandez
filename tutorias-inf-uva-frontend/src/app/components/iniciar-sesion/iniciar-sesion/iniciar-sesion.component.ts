import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { Profesor } from 'src/app/models/profesor';
import { Usuario } from 'src/app/models/usuario';
import { SesionService } from 'src/app/services/sesion.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-iniciar-sesion',
  templateUrl: './iniciar-sesion.component.html',
  styleUrls: ['./iniciar-sesion.component.scss']
})
export class IniciarSesionComponent implements OnInit {

  loginForm: FormGroup = this.fb.group({
    username: [null, Validators.required],
    password: [null, Validators.required]
  });

  constructor(
    private usuarioService: UsuarioService,
    private sesionService: SesionService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
  }

  onSubmit(formValues: any): void {
    const username = formValues.username.trim();
    const password = formValues.password;

    this.usuarioService.login(username, password).subscribe(data => {
      if (data) {
        let usuarioLogeado: Usuario;

        if (data.nia) {
          usuarioLogeado = new Alumno(data);
        } else {
          usuarioLogeado = new Profesor(data);
        }

        this.sesionService.setUsuarioLogeado(usuarioLogeado);
        this.router.navigate(['/home']);
      }
    });
  }

}
