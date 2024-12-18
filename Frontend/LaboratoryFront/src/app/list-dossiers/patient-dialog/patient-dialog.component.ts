import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AjouterPatientComponent } from '../../ajouter-patient/ajouter-patient.component';
import { AjouterDossierComponent } from '../../ajouter-dossier/ajouter-dossier.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ListDossiersComponent } from '../list-dossiers.component';

@Component({
  selector: 'app-dialog-example',
  styleUrls: ['./patient-dialog.component.css'],
  imports: [
    CommonModule,
    FormsModule, 
   // Pour formulaires réactifs
  ],

  template: `
    <div mat-dialog-content>
      <p>Veuillez choisir une option :</p>
    </div>
    <div mat-dialog-actions>
      <button mat-button (click)="openNouveauPatient()">Nouveau Patient</button>
      <button mat-button (click)="openPatientExistant()">Patient Existant</button>
    </div>
  `,
})
export class PatientDialogComponent {
  constructor(private dialog: MatDialog) {}


 
  openNouveauPatient(): void {
    this.dialog.open(AjouterPatientComponent, {
    width: '2000px',
    height:'1000px',
    });
    }

    openPatientExistant(): void {
      this.dialog.open(ListDossiersComponent, {
      width: '2000px',
      height:'1000px',
      });
      }
}
