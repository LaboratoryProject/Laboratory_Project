import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

interface Adresse {
  codePostal: string;
  commune: string;
  id: number;
  nomVoie: string;
  numVoie: string;
  ville: string;
}

interface ContactLaboratoire {
  email: string;
  fax: string;
  fkIdAdresse: number;
  fkIdLaboratoire: number;
  id: number;
  numTel: string;
}

interface Laboratoire {
  id: number;
  nom: string;
  nrc: string;
  logo: string;
  active: boolean;
  dateActivation: string;
}

interface LaboratoireDetails {
  laboratoire: Laboratoire;
  adresse: Adresse;
  contactLaboratoire: ContactLaboratoire;
}

@Component({
  selector: 'app-laboratoire-details-dialog',
  templateUrl: './laboratoire-details-dialog.component.html',
  styleUrls: ['./laboratoire-details-dialog.component.css'],
  standalone: true,
  imports: [
    CommonModule, 
    MatDialogModule, 
    MatButtonModule
  ]
})
export class LaboratoireDetailsDialogComponent {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: LaboratoireDetails,
    public dialogRef: MatDialogRef<LaboratoireDetailsDialogComponent>
  ) {}

  // Method to close the dialog programmatically if needed
  closeDialog() {
    this.dialogRef.close();
  }
}