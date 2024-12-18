import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { EpreuveService } from '../epreuve.service';

@Component({
  selector: 'app-ajouter-epreuve',
  templateUrl: './ajouter-epreuve.component.html',
  styleUrls: ['./ajouter-epreuve.component.css'],
  standalone:true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
  ],
})
export class AjouterEpreuveComponent {
  epreuveForm: FormGroup;

  constructor(private fb: FormBuilder, private epreuveService: EpreuveService) {
    this.epreuveForm = this.fb.group({
      nomEpreuve: ['', Validators.required],
      fkIdAnalyse: [null, [Validators.required, Validators.min(1)]], // ID d'analyse associé obligatoire
    });
  }

  onSubmit(): void {
    if (this.epreuveForm.valid) {
      console.log('Données soumises :', this.epreuveForm.value);
      this.epreuveService.createEpreuve(this.epreuveForm.value).subscribe(
        () => {
          alert('Épreuve ajoutée avec succès');
          this.epreuveForm.reset(); // Réinitialise le formulaire après soumission
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de l\'épreuve:', error);
          alert('Erreur lors de l\'ajout de l\'épreuve');
        }
      );
    } else {
      alert('Veuillez remplir tous les champs requis');
    }
  }

  revenir(): void {
    history.back(); // Retourne à la page précédente
  }
}
