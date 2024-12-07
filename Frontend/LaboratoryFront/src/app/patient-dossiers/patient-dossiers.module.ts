import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientDossiersComponent } from './patient-dossiers.component';

@NgModule({
  declarations: [
    PatientDossiersComponent // Declare the component here
  ],
  imports: [
    CommonModule // Contains common Angular directives like *ngIf, *ngFor
  ],
  exports: [
    PatientDossiersComponent // Export if necessary for other modules
  ]
})
export class PatientDossiersModule { }
