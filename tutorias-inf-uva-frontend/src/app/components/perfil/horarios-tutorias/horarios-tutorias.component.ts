import { Component, Input, OnInit } from '@angular/core';
import { DialogService } from 'primeng/dynamicdialog';
import { HorarioTutorias } from 'src/app/models/horario-tutorias';
import { MessageService } from 'src/app/services/message.service';
import { CargarHorarioTutoriasComponent } from '../cargar-horario-tutorias/cargar-horario-tutorias.component';

@Component({
  selector: 'app-horarios-tutorias',
  templateUrl: './horarios-tutorias.component.html',
  styleUrls: ['./horarios-tutorias.component.scss']
})
export class HorariosTutoriasComponent implements OnInit {

  @Input() horariosTutorias: HorarioTutorias[] = [];
  @Input() showCargarHorarioButton = false;

  constructor(
    private dialogService: DialogService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
  }

  showCargarHorarioDialog(): void {
    const dialogRef = this.dialogService.open(CargarHorarioTutoriasComponent, {
      header: 'Cargar horario de tutorías',
      width: '60%',
    });

    dialogRef.onClose.subscribe((nuevosHorarios: HorarioTutorias[]) => {
      if (nuevosHorarios?.length == 0) {
        this.messageService.showErrorMessage("El fichero no cumple con el formato")
      }
      if (nuevosHorarios?.length > 0) {
        this.horariosTutorias = nuevosHorarios;
        this.messageService.showSuccessMessage('Horario cargado con éxito')
      }
    });
  }
}
