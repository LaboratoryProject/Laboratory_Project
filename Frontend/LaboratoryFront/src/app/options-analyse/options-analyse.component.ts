import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Import correct pour Angular
import { PatientDialogComponent } from '../list-dossiers/patient-dialog/patient-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-options-analyse',
  templateUrl: './options-analyse.component.html',
  styleUrls: ['./options-analyse.component.css'] // Utilisez 'styleUrls' au lieu de 'styleUrl'
})
export class OptionsAnalyseComponent {
  constructor(private router: Router, private dialog: MatDialog)  {}



  openDialog(): void {
    this.dialog.open(PatientDialogComponent, {
    width: '300px',
    data: { message: 'Hello from the dialog!' }
    });
    }
}
