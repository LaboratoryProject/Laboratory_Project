import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AjouterUtilisateurComponent } from './ajouter-utilisateur.component';

@NgModule({
  declarations: [
    AjouterUtilisateurComponent // Déclarer le composant ici
  ],
  imports: [
    CommonModule,  // Contient les directives Angular de base comme *ngIf, *ngFor
    FormsModule    // Si le composant utilise [(ngModel)] ou des formulaires
  ],
  exports: [
    AjouterUtilisateurComponent // Exporter si nécessaire pour d'autres modules
  ]
})
export class AjouterUtilisateurModule { }
