import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuscarProfesorComponent } from './components/buscar-profesor/buscar-profesor/buscar-profesor.component';
import { BusquedaPorAsignaturaComponent } from './components/buscar-profesor/busqueda-por-asignatura/busqueda-por-asignatura.component';
import { BusquedaPorProfesorComponent } from './components/buscar-profesor/busqueda-por-profesor/busqueda-por-profesor.component';
import { CalendarioTutoriasComponent } from './components/calendario-tutorias/calendario-tutorias/calendario-tutorias.component';
import { ConsultarSolicitudesTutoriaComponent } from './components/consultar-solicitudes-tutoria/consultar-solicitudes-tutoria/consultar-solicitudes-tutoria.component';
import { SolicitudTutoriaComponent } from './components/solicitud-tutoria/solicitud-tutoria/solicitud-tutoria.component';
import { ConsultarTutoriasComponent } from './components/consultar-tutorias/consultar-tutorias/consultar-tutorias.component';
import { FiltrarTutoriasComponent } from './components/consultar-tutorias/filtrar-tutorias/filtrar-tutorias.component';
import { ProximasTutoriasComponent } from './components/consultar-tutorias/proximas-tutorias/proximas-tutorias.component';
import { TutoriasHoyComponent } from './components/consultar-tutorias/tutorias-hoy/tutorias-hoy.component';
import { InformacionProfesorComponent } from './components/informacion-profesor/informacion-profesor/informacion-profesor.component';
import { IniciarSesionComponent } from './components/iniciar-sesion/iniciar-sesion/iniciar-sesion.component';
import { InicioComponent } from './components/inicio/inicio/inicio.component';
import { SolicitarTutoriaComponent } from './components/solicitar-tutoria/solicitar-tutoria/solicitar-tutoria.component';
import { AuthGuard } from './guards/auth.guard';
import { TutoriaComponent } from './components/tutoria/tutoria/tutoria.component';
import { PerfilComponent } from './components/perfil/perfil/perfil.component';
import { AlumnoGuard } from './guards/alumno.guard';
import { NotLoggedInGuard } from './guards/not-logged-in.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'login', component: IniciarSesionComponent, canActivate: [NotLoggedInGuard] },
  { path: 'home', component: InicioComponent, canActivate: [AuthGuard] },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },
  { path: 'buscar-profesor', component: BuscarProfesorComponent, canActivate: [AuthGuard, AlumnoGuard],
    children: [
      { path: 'busqueda-por-profesor', component: BusquedaPorProfesorComponent },
      { path: 'busqueda-por-asignatura', component: BusquedaPorAsignaturaComponent },
      { path: '', pathMatch: 'full', redirectTo: 'busqueda-por-profesor' }
    ] 
  },
  { path: 'informacion-profesor/:identificador', component: InformacionProfesorComponent, canActivate: [AuthGuard, AlumnoGuard] },
  { path: 'solicitar-tutoria', component: SolicitarTutoriaComponent, canActivate: [AuthGuard] },
  { path: 'consultar-solicitudes', component: ConsultarSolicitudesTutoriaComponent, canActivate: [AuthGuard] },
  { path: 'solicitudes/:solicitudId', component: SolicitudTutoriaComponent, canActivate: [AuthGuard] },
  { path: 'calendario-tutorias', component: CalendarioTutoriasComponent, canActivate: [AuthGuard] },
  { path: 'consultar-tutorias', component: ConsultarTutoriasComponent, canActivate: [AuthGuard],
    children: [
      { path: 'proximamente', component: ProximasTutoriasComponent },
      { path: 'hoy', component: TutoriasHoyComponent },
      { path: 'filtrar', component: FiltrarTutoriasComponent },
      { path: '', pathMatch: 'full', redirectTo: 'proximamente' }
    ]
  },
  { path: 'tutorias/:tutoriaId', component: TutoriaComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
