import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-analyse-dialog',
  templateUrl: './add-analyse-dialog.component.html',
  styleUrls: ['./add-analyse-dialog.component.css'],
})
export class AddAnalyseDialogComponent {
  cin: string = '';

  constructor(
    private dialogRef: MatDialogRef<AddAnalyseDialogComponent>,
    private router: Router
  ) {}

  checkPatient(): void {
    if (this.cin) {
      // Rediriger vers la page des dossiers ou créer un patient
      this.router.navigate(['/patient-dossiers', this.cin]);
      this.dialogRef.close();
    }
  }
}
