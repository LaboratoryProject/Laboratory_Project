import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-patient-modification-dialog',
  templateUrl: './patient-modification-dialog.component.html',
  imports: [CommonModule, FormsModule,ReactiveFormsModule],
  styleUrls: ['./patient-modification-dialog.component.css']
})
export class PatientModificationDialogComponent implements OnInit {
  
  // Formulaire pour la modification des données
  modificationForm: FormGroup;

  // Formulaire pour l'ajout d'un nouveau patient
  ajoutForm: FormGroup;

  constructor(private fb: FormBuilder) {
    // Création du formulaire de modification
    this.modificationForm = this.fb.group({
      numTel: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]]
    });

    // Création du formulaire pour ajouter un nouveau patient
    this.ajoutForm = this.fb.group({
      nomComplet: ['', Validators.required],
      dateNaissance: ['', Validators.required],
      lieuDeNaissance: ['', Validators.required],
      sexe: ['', Validators.required],
      typePieceIdentite: ['', Validators.required],
      numPieceIdentite: ['', Validators.required],
      adresse: ['', Validators.required],
      numTel: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      visible_pour: [false]
    });
  }

  ngOnInit(): void {}

  // Fonction soumission pour la modification
  onSubmitModification() {
    if (this.modificationForm.valid) {
      console.log('Formulaire de modification soumis : ', this.modificationForm.value);
      alert('Informations du patient modifiées avec succès');
    } else {
      alert('Formulaire de modification invalide');
    }
  }

  // Fonction soumission pour l'ajout
  onSubmitAjout() {
    if (this.ajoutForm.valid) {
      console.log('Formulaire d\'ajout soumis : ', this.ajoutForm.value);
      alert('Nouveau patient ajouté avec succès');
    } else {
      alert('Formulaire d\'ajout invalide');
    }
  }
}
