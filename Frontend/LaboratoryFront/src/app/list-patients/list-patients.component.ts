import { Component } from '@angular/core';
import { PatientService } from '../services/patient.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PatientModificationDialogComponent } from './patient-modification-dialog/patient-modification-dialog.component';
@Component({
  selector: 'app-list-patients',
  standalone: true,
  imports: [CommonModule, FormsModule,ReactiveFormsModule],
  
  templateUrl: './list-patients.component.html',
  styleUrl: './list-patients.component.css'
})
export class ListPatientsComponent {
  patients: any[] = []; // Pour stocker la liste des patients

  constructor(
    private patientService: PatientService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.listerPatients();
  }

  // Liste des patients
  listerPatients() {
    this.patientService.listerPatients().subscribe({
      next: (data) => {
        this.patients = data; // Mettre à jour la liste avec les données du serveur
      },
      error: (error) => {
        console.error('Erreur lors du chargement des patients', error);
      },
    });
  }

  // Fonction pour supprimer un patient
  supprimerPatient(id: number) {
    this.patientService.supprimerPatient(id).subscribe({
      next: () => {
        alert('Patient supprimé avec succès');
        this.listerPatients(); // Mettre à jour la liste après suppression
      },
      error: (error) => {
        console.error('Erreur lors de la suppression', error);
      },
    });
  }

  // Fonction pour modifier un patient (exemple : mettre à jour le téléphone)
  modifierPatient(patient: any) {
    const dialogRef = this.dialog.open(PatientModificationDialogComponent, {
      width: '500px',
      data: { patientId: patient.id } // Passer l'ID du patient au dialogue
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Actualiser la liste des patients si nécessaire
        this.listerPatients();
      }
    });
  }

  ouvrirFormulaireAjout() {
    this.router.navigate(['/add-patient']);
  }
}  




