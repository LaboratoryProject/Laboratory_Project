import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { PatientService } from '../services/patient.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ajouter-patient',
  templateUrl: './ajouter-patient.component.html',
  styleUrls: ['./ajouter-patient.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
})
export class AjouterPatientComponent {
  patientForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private patientService: PatientService
  ) {
    this.patientForm = this.fb.group({
      nomComplet: ['', Validators.required],
      dateNaissance: ['', Validators.required],
      lieuDeNaissance: ['', Validators.required],
      sexe: ['', Validators.required],
      typePieceIdentite: ['', Validators.required],
      numPieceIdentite: ['', Validators.required],
      adresse: ['', Validators.required],
      numTel: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      visible_pour: [false],
    });
  }

  onSubmit() {
    if (this.patientForm.valid) {
      this.patientService.ajouterPatient(this.patientForm.value).subscribe({
        next: (response) => {
          console.log('Patient ajouté avec succès', response);
          alert('Patient ajouté avec succès');
          // Redirigez vers une autre page si nécessaire
          this.router.navigate(['/add-dossier']);
        },
        error: (err) => {
          console.error('Erreur lors de l’ajout du patient', err);
          alert('Une erreur est survenue, veuillez réessayer.');
        },
      });
    } else {
      alert('Veuillez remplir tous les champs requis');
    }
  }
}
