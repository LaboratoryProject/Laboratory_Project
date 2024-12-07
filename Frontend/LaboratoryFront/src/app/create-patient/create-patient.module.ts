import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CreatePatientComponent } from './create-patient.component';

@NgModule({
  declarations: [
    CreatePatientComponent // Declare the component here
  ],
  imports: [
    CommonModule,       // Contains common Angular directives like *ngIf, *ngFor
    FormsModule,        // If the component uses [(ngModel)] or forms
    MatFormFieldModule, // Material form field module
    MatInputModule,     // Material input module
    MatButtonModule,    // Material button module
    MatSnackBarModule   // Material snackbar module
  ],
  exports: [
    CreatePatientComponent // Export if necessary for other modules
  ]
})
export class CreatePatientModule { }
