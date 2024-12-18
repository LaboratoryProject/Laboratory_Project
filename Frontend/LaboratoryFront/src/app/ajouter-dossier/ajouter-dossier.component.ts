// ajouter-dossier.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface Analyse {
  id: number;
  nom: string;
}

@Component({
  selector: 'app-ajouter-dossier',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ajouter-dossier.component.html',
  styleUrls: ['./ajouter-dossier.component.css']
})
export class AjouterDossierComponent {
  dossierForm: FormGroup;
  analyses: Analyse[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient,  private router: Router) {
    this.dossierForm = this.fb.group({
      numDossier: ['', Validators.required],
      emailUtilisateur: ['', [Validators.required, Validators.email]],
      cinPatient: ['', Validators.required],
      date: ['', Validators.required],
      analyse: ['', Validators.required]
    });

    this.loadAnalyses();
  }

  loadAnalyses() {
    // Récupération de la liste des analyses depuis l'API
    this.http.get<Analyse[]>('http://your-api-url.com/analyses').subscribe(
      (data) => {
        this.analyses = data;
      },
      (error) => {
        console.error('Erreur lors de la récupération des analyses', error);
      }
    );
  }

  submitForm() {
    if (this.dossierForm.valid) {
      console.log('Formulaire soumis avec les données : ', this.dossierForm.value);
      alert('Dossier créé avec succès');
          // Navigation vers l'autre route
      this.router.navigate(['/list-dossiers']);
    } else {
      alert('Veuillez remplir correctement tous les champs');
    }
  }
}