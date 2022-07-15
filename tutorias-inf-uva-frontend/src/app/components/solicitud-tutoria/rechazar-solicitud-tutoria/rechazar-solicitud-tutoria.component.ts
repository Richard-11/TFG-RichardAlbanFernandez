import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef } from 'primeng/dynamicdialog';

@Component({
  selector: 'app-rechazar-solicitud-tutoria',
  templateUrl: './rechazar-solicitud-tutoria.component.html',
  styleUrls: ['./rechazar-solicitud-tutoria.component.scss']
})
export class RechazarSolicitudTutoriaComponent implements OnInit {

  rechazarSolicitudForm: FormGroup = this.fb.group({
    motivoRechazo: [null, [Validators.required, Validators.minLength(10)]]
  });

  constructor(
    private fb: FormBuilder,
    private dialogRef: DynamicDialogRef
  ) { }

  ngOnInit(): void {
  }

  onSubmit(formValues: any): void {
    const motivoRechazo = formValues.motivoRechazo;
    this.dialogRef.close(motivoRechazo);
  }

  cancelar() {
    this.dialogRef.close();
  }

}
