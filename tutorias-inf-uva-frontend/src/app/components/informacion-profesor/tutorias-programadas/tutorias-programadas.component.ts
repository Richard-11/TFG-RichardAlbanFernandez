import { Component, Input, OnInit } from '@angular/core';
import { CalendarOptions, EventClickArg, EventInput } from '@fullcalendar/angular';
import esLocale from '@fullcalendar/core/locales/es';
import { DialogService } from 'primeng/dynamicdialog';
import { Profesor } from 'src/app/models/profesor';
import { Tutoria } from 'src/app/models/tutoria';
import { EventService } from 'src/app/services/event.service';
import { TutoriaService } from 'src/app/services/tutoria.service';
import { DetalleTutoriaComponent } from '../../calendario-tutorias/detalle-tutoria/detalle-tutoria.component';

@Component({
  selector: 'app-tutorias-programadas',
  templateUrl: './tutorias-programadas.component.html',
  styleUrls: ['./tutorias-programadas.component.scss'],
  providers: [DialogService]
})
export class TutoriasProgramadasComponent implements OnInit {

  @Input() profesor!: Profesor;

  calendarOptions: CalendarOptions = {
    initialView: 'timeGridWeek',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'timeGridWeek,timeGridDay'
    },
    locale: esLocale,
    weekends: false,
    allDaySlot: false,
    nowIndicator: true,
    eventClick: this.handleEventClick.bind(this)
  }

  events: EventInput[] = [];

  constructor(
    private tutoriaService: TutoriaService,
    private eventService: EventService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.tutoriaService.getTutorias(this.profesor, { cancelada: false }).subscribe(tutorias => {
      this.calendarOptions.events = this.eventService.convertirTutoriasEnEventos(tutorias)
    });
  }

  handleEventClick(clickInfo: EventClickArg) {
    const tutoria = clickInfo.event.extendedProps["tutoria"];
    if (tutoria) {
      this.mostrarDetalleTutoria(tutoria);
    }
  }

  private mostrarDetalleTutoria(tutoria: Tutoria): void {
    this.dialogService.open(DetalleTutoriaComponent, {
      header: 'Detalle tutoría',
      width: '500px',
      data: {
        tutoria: { ...tutoria }
      }
    });
  }

}
