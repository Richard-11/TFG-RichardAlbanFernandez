import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef } from 'primeng/dynamicdialog';

@Component({
  selector: 'app-cancelar-tutoria',
  templateUrl: './cancelar-tutoria.component.html',
  styleUrls: ['./cancelar-tutoria.component.scss']
})
export class CancelarTutoriaComponent implements OnInit {

  cancelarTutoriaForm: FormGroup = this.fb.group({
    motivoCancelacion: [null, [Validators.required, Validators.minLength(10)]]
  });

  constructor(
    private fb: FormBuilder,
    private dialogRef: DynamicDialogRef
  ) { }

  ngOnInit(): void {
  }

  onSubmit(formValues: any): void {
    const motivoCancelacion = formValues.motivoCancelacion;
    this.dialogRef.close(motivoCancelacion);
  }

  cancelar() {
    this.dialogRef.close();
  }
  
}
