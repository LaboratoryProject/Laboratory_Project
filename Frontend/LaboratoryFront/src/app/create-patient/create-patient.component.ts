import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PatientService } from '../services/patient.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.css'],
})
export class CreatePatientComponent {
  patientForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private patientService: PatientService,
    private snackBar: MatSnackBar
  ) {
    this.patientForm = this.fb.group({
      cin: ['', [Validators.required, Validators.minLength(8)]],
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      dateNaissance: ['', Validators.required],
      telephone: ['', [Validators.required, Validators.pattern(/^(\+212|0)[5-7]\d{8}$/)]],
      adresse: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.patientForm.valid) {
      this.patientService.createPatient(this.patientForm.value).subscribe(
        () => {
          this.snackBar.open('Patient créé avec succès !', 'Fermer', { duration: 3000 });
          this.patientForm.reset();
        },
        (error) => {
          console.error(error);
          this.snackBar.open('Erreur lors de la création du patient.', 'Fermer', { duration: 3000 });
        }
      );
    } else {
      this.snackBar.open('Veuillez remplir tous les champs correctement.', 'Fermer', { duration: 3000 });
    }
  }
}
