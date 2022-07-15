import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { Papa, ParseConfig, ParseResult } from 'ngx-papaparse';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { ElementoFicheroHorariosTutorias } from 'src/app/models/elemento-fichero-horarios-tutorias';
import { TipoHorarioEnum } from 'src/app/models/enums/tipo-horario-enum';
import { FranjaTutorias } from 'src/app/models/franja-tutorias';
import { HorarioTutorias } from 'src/app/models/horario-tutorias';
import { Profesor } from 'src/app/models/profesor';
import { TipoHorario } from 'src/app/models/tipo-horario';
import { HorarioTutoriasService } from 'src/app/services/horario-tutorias.service';
import { SesionService } from 'src/app/services/sesion.service';
import { MOMENT_TIME_FORMAT } from 'src/app/utils/constantes.utils';
import { DiaUtils } from 'src/app/utils/dia.utils';

@Component({
  selector: 'app-cargar-horario-tutorias',
  templateUrl: './cargar-horario-tutorias.component.html',
  styleUrls: ['./cargar-horario-tutorias.component.scss']
})
export class CargarHorarioTutoriasComponent implements OnInit {
  profesorLogueado!: Profesor;

  uploadFile: any;
  maxFileSize = 1000000;

  constructor(
    private horarioTutoriasService: HorarioTutoriasService,
    private sesionService: SesionService,
    private papa: Papa,
    private dialogRef: DynamicDialogRef,
  ) { }

  ngOnInit(): void {
    if (this.sesionService.getUsuarioLogueado() != null) {
      this.profesorLogueado = this.sesionService.getUsuarioLogueado() as Profesor;
    }
  }

  onUpload(event: any) {
    let file: File = event.files[0];
    let reader = new FileReader();

    reader.readAsText(file);

    let csvContent;
    reader.onload = (ev: ProgressEvent<FileReader>) => {
      csvContent = reader.result;
      let horarios = this.convertCSVToObject(csvContent as string);

      if (horarios.length == 0) {
        this.dialogRef.close(horarios);
      }

      if (horarios.length > 0) {
        this.horarioTutoriasService.nuevosHorariosTutorias(this.profesorLogueado, horarios).subscribe((horarios: HorarioTutorias[]) => {
          if (horarios) {
            this.dialogRef.close(horarios);
          } else {
            this.dialogRef.close();
          }
        });
      }
    }
  }

  public convertCSVToObject(csvContent: string): HorarioTutorias[] {
    let config: ParseConfig = {
      delimiter: ';',
      encoding: 'utf-8',
      header: true,
      skipEmptyLines: true
    }

    let result: ParseResult = this.papa.parse(csvContent, config);

    return this.convertToHorariosTutorias(result);
  }

  private convertToHorariosTutorias(result: ParseResult): HorarioTutorias[] {
    const data = result.data as ElementoFicheroHorariosTutorias[];

    if (result.errors.length > 0) {
      return [];
    }

    let franjasPrimerCuatrimestre = data.filter((f: ElementoFicheroHorariosTutorias) => Number(f.tipoHorario) === TipoHorarioEnum.PRIMER_CUATRIMESTRE);
    let franjasSegundoCuatrimestre = data.filter((f: ElementoFicheroHorariosTutorias) => Number(f.tipoHorario) === TipoHorarioEnum.SEGUNDO_CUATRIMESTRE);
    let franjasAnual = data.filter((f: ElementoFicheroHorariosTutorias) => Number(f.tipoHorario) === TipoHorarioEnum.ANUAL);

    if (franjasAnual.length > 0 && (franjasPrimerCuatrimestre.length > 0 || franjasSegundoCuatrimestre.length > 0)) {
      return [];
    }

    if (franjasAnual.length > 0) {
      let tipoHorario = new TipoHorario(TipoHorarioEnum.ANUAL);
      let franjas = this.convertToFranjas(franjasAnual);

      if (!franjas) {
        return [];
      }

      let horarioTutorias = new HorarioTutorias(undefined, tipoHorario, franjas);
      return [horarioTutorias];
    }

    let horariosTutorias: HorarioTutorias[] = [];
    if (franjasPrimerCuatrimestre.length > 0) {
      let tipoHorario = new TipoHorario(TipoHorarioEnum.PRIMER_CUATRIMESTRE);
      let franjas = this.convertToFranjas(franjasPrimerCuatrimestre);

      if (!franjas) {
        return [];
      }

      let horarioTutorias = new HorarioTutorias(undefined, tipoHorario, franjas);
      horariosTutorias.push(horarioTutorias);
    }

    if (franjasSegundoCuatrimestre.length > 0) {
      let tipoHorario = new TipoHorario(TipoHorarioEnum.SEGUNDO_CUATRIMESTRE);
      let franjas = this.convertToFranjas(franjasSegundoCuatrimestre);

      if (!franjas) {
        return [];
      }

      let horarioTutorias = new HorarioTutorias(undefined, tipoHorario, franjas);
      horariosTutorias.push(horarioTutorias);
    }

    return horariosTutorias;
  }

  private convertToFranjas(data: any): FranjaTutorias[] | false {
    let franjas: FranjaTutorias[] = [];
    let franjasNoValidas = false;

    for (const element of data) {
      let franja: FranjaTutorias = new FranjaTutorias();
      const dia = DiaUtils.getDiaByDiaString(this.removeAccents(element.dia.trim()));
      if (dia == null) {
        franjasNoValidas = true;
        break;
      }
      franja.dia = dia;
      franja.horaInicio = moment(element.horaInicio.trim(), MOMENT_TIME_FORMAT).format(MOMENT_TIME_FORMAT);
      franja.horaFin = moment(element.horaFin.trim(), MOMENT_TIME_FORMAT).format(MOMENT_TIME_FORMAT);
      franja.centro = element.centro.trim();
      franja.despacho = element.despacho.trim();

      franjas.push(franja);
    }

    if (franjasNoValidas) {
      return false;
    }

    return franjas;
  }

  private removeAccents(str: string): string {
    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
  }

}