import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PatientService } from '../../services/patient.service';


@Component({
  selector: 'app-patient-modification-dialog',
  standalone: true,
  templateUrl: './patient-modification-dialog.component.html',
  styleUrls: ['./patient-modification-dialog.component.css']
})
export class PatientModificationDialogComponent implements OnInit {
  @Input() patientId!: number; // Recevoir l'ID du patient
  patientForm!: FormGroup;

  constructor(private fb: FormBuilder, private patientService: PatientService) {}

  ngOnInit(): void {
    this.initForm();
    this.loadPatientData();
  }

  // Initialise le formulaire réactif
  initForm() {
    this.patientForm = this.fb.group({
      numTel: ['', [Validators.required, Validators.pattern(/^[0-9]{10}$/)]], // Exemple : Validation d'un numéro de téléphone
    });
  }

  // Charge les données existantes du patient
  loadPatientData() {
    this.patientService.getPatientById(this.patientId).subscribe({
      next: (data) => {
        this.patientForm.patchValue({
          numTel: data.numTel
        });
      },
      error: (error) => {
        console.error('Erreur lors du chargement des données', error);
      },
    });
  }

  // Soumet les changements
  onSubmit() {
    if (this.patientForm.valid) {
      const updatedData = { numTel: this.patientForm.value.numTel };
      this.patientService.modifierPatient(this.patientId, updatedData).subscribe({
        next: () => {
          alert('Patient mis à jour avec succès');
        },
        error: (error) => {
          console.error('Erreur lors de la mise à jour', error);
          alert('Erreur lors de la mise à jour');
        },
      });
    } else {
      alert('Formulaire invalide');
    }
  }
}
