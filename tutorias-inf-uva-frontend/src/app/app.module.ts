import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AccordionModule } from 'primeng/accordion';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { CarouselModule } from 'primeng/carousel';
import { DialogModule } from 'primeng/dialog';
import { DividerModule } from 'primeng/divider';
import { DropdownModule } from 'primeng/dropdown';
import { DialogService, DynamicDialogModule } from 'primeng/dynamicdialog';
import { FieldsetModule } from 'primeng/fieldset';
import { FileUploadModule } from 'primeng/fileupload';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { PasswordModule } from 'primeng/password';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { TabViewModule } from 'primeng/tabview';
import { TooltipModule } from 'primeng/tooltip';
import { TriStateCheckboxModule } from 'primeng/tristatecheckbox';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BuscarProfesorComponent } from './components/buscar-profesor/buscar-profesor/buscar-profesor.component';
import { BusquedaPorAsignaturaComponent } from './components/buscar-profesor/busqueda-por-asignatura/busqueda-por-asignatura.component';
import { BusquedaPorProfesorComponent } from './components/buscar-profesor/busqueda-por-profesor/busqueda-por-profesor.component';
import { CalendarioTutoriasComponent } from './components/calendario-tutorias/calendario-tutorias/calendario-tutorias.component';
import { DetalleSolicitudPendienteConfirmarComponent } from './components/calendario-tutorias/detalle-solicitud-pendiente-confirmar/detalle-solicitud-pendiente-confirmar.component';
import { DetalleTutoriaComponent } from './components/calendario-tutorias/detalle-tutoria/detalle-tutoria.component';
import { ConsultarSolicitudesTutoriaComponent } from './components/consultar-solicitudes-tutoria/consultar-solicitudes-tutoria/consultar-solicitudes-tutoria.component';
import { FiltrosSolicitudTutoriaComponent } from './components/consultar-solicitudes-tutoria/filtros-solicitud-tutoria/filtros-solicitud-tutoria.component';
import { ListadoSolicitudesTutoriaComponent } from './components/consultar-solicitudes-tutoria/listado-solicitudes-tutoria/listado-solicitudes-tutoria.component';
import { ConsultarTutoriasComponent } from './components/consultar-tutorias/consultar-tutorias/consultar-tutorias.component';
import { FiltrarTutoriasComponent } from './components/consultar-tutorias/filtrar-tutorias/filtrar-tutorias.component';
import { FiltrosTutoriaComponent } from './components/consultar-tutorias/filtros-tutoria/filtros-tutoria.component';
import { ListadoTutoriasComponent } from './components/consultar-tutorias/listado-tutorias/listado-tutorias.component';
import { ProximasTutoriasComponent } from './components/consultar-tutorias/proximas-tutorias/proximas-tutorias.component';
import { TutoriasHoyComponent } from './components/consultar-tutorias/tutorias-hoy/tutorias-hoy.component';
import { InformacionProfesorComponent } from './components/informacion-profesor/informacion-profesor/informacion-profesor.component';
import { TutoriasProgramadasComponent } from './components/informacion-profesor/tutorias-programadas/tutorias-programadas.component';
import { IniciarSesionComponent } from './components/iniciar-sesion/iniciar-sesion/iniciar-sesion.component';
import { InicioComponent } from './components/inicio/inicio/inicio.component';
import { AsignaturasComponent } from './components/perfil/asignaturas/asignaturas.component';
import { DatosAlumnoComponent } from './components/perfil/datos-alumno/datos-alumno.component';
import { DatosProfesorComponent } from './components/perfil/datos-profesor/datos-profesor.component';
import { DatosUsuarioComponent } from './components/perfil/datos-usuario/datos-usuario.component';
import { HorariosTutoriasComponent } from './components/perfil/horarios-tutorias/horarios-tutorias.component';
import { PerfilComponent } from './components/perfil/perfil/perfil.component';
import { SolicitarTutoriaComponent } from './components/solicitar-tutoria/solicitar-tutoria/solicitar-tutoria.component';
import { AceptarSolicitudTutoriaComponent } from './components/solicitud-tutoria/aceptar-solicitud-tutoria/aceptar-solicitud-tutoria.component';
import { RechazarSolicitudTutoriaComponent } from './components/solicitud-tutoria/rechazar-solicitud-tutoria/rechazar-solicitud-tutoria.component';
import { SolicitudTutoriaComponent } from './components/solicitud-tutoria/solicitud-tutoria/solicitud-tutoria.component';
import { CancelarTutoriaComponent } from './components/tutoria/cancelar-tutoria/cancelar-tutoria.component';
import { TutoriaComponent } from './components/tutoria/tutoria/tutoria.component';
import { HeaderComponent } from './components/ui/header/header.component';
import { SidebarComponent } from './components/ui/sidebar/sidebar.component';
import { SolicitudesInicioComponent } from './components/inicio/solicitudes-inicio/solicitudes-inicio.component';
import { TutoriasInicioComponent } from './components/inicio/tutorias-inicio/tutorias-inicio.component';
import { CargarHorarioTutoriasComponent } from './components/perfil/cargar-horario-tutorias/cargar-horario-tutorias.component';
import { RightSidebarComponent } from './components/ui/right-sidebar/right-sidebar.component';

FullCalendarModule.registerPlugins([
  dayGridPlugin,
  timeGridPlugin,
  interactionPlugin,
]);

export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient, './assets/i18n/', '.json')
}
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    BuscarProfesorComponent,
    BusquedaPorProfesorComponent,
    BusquedaPorAsignaturaComponent,
    InformacionProfesorComponent,
    AsignaturasComponent,
    TutoriasProgramadasComponent,
    SolicitarTutoriaComponent,
    HorariosTutoriasComponent,
    ConsultarSolicitudesTutoriaComponent,
    FiltrosSolicitudTutoriaComponent,
    ListadoSolicitudesTutoriaComponent,
    SolicitudTutoriaComponent,
    IniciarSesionComponent,
    InicioComponent,
    AceptarSolicitudTutoriaComponent,
    RechazarSolicitudTutoriaComponent,
    CalendarioTutoriasComponent,
    DetalleTutoriaComponent,
    DetalleSolicitudPendienteConfirmarComponent,
    ConsultarTutoriasComponent,
    ProximasTutoriasComponent,
    TutoriasHoyComponent,
    FiltrarTutoriasComponent,
    FiltrosTutoriaComponent,
    ListadoTutoriasComponent,
    TutoriaComponent,
    CancelarTutoriaComponent,
    PerfilComponent,
    DatosUsuarioComponent,
    DatosProfesorComponent,
    DatosAlumnoComponent,
    SolicitudesInicioComponent,
    TutoriasInicioComponent,
    CargarHorarioTutoriasComponent,
    RightSidebarComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    TranslateModule.forRoot({
      defaultLanguage: 'es',
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    TabMenuModule,
    TabViewModule,
    InputTextModule,
    InputTextareaModule,
    TriStateCheckboxModule,
    PasswordModule,
    CheckboxModule,
    ButtonModule,
    TooltipModule,
    FieldsetModule,
    DividerModule,
    DialogModule,
    DynamicDialogModule,
    DropdownModule,
    AutoCompleteModule,
    TableModule,
    AccordionModule,
    MessageModule,
    MessagesModule,
    DialogModule,
    CardModule,
    ConfirmDialogModule,
    CarouselModule,
    FileUploadModule,
    CalendarModule,
    FullCalendarModule
  ],
  providers: [MessageService, DialogService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
