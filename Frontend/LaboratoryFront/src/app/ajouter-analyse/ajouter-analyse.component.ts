import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AnalyseService } from '../analyse.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ajouter-analyse',
  templateUrl: './ajouter-analyse.component.html',
  styleUrls: ['./ajouter-analyse.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
})

export class AjouterAnalyseComponent {
  analyseForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router)  {
    this.analyseForm = this.fb.group({
      nomAnalyse: ['', Validators.required],
      descriptionAnalyse: ['', Validators.required],
      laboratoire: ['', Validators.required],
      statutAnalyse: ['En cours', Validators.required],
      epreuve: ['', Validators.required],
      dateAnalyse: ['', Validators.required],
      coutAnalyse: ['', [Validators.required, Validators.min(0)]],
    });
  }

   onSubmit() {
      if (this.analyseForm.valid) {
        console.log('Données soumises :', this.analyseForm.value);
        alert('Analyse ajoutée avec succès');
      } else {
        alert('Veuillez remplir tous les champs requis');
      }
    }

  navigateToAjouter() {
         this.router.navigate(['/ajouter-epreuve']);
       }

    // Réinitialisation du formulaire
    revenir(): void {
        history.back(); // Retourne à la page précédente
      }
}
