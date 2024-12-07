import { Component } from '@angular/core';
import { UtilisateurService } from '../utilisateur.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modifier-utilisateur',
  standalone: true,
  templateUrl: './modifier-utilisateur.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./modifier-utilisateur.component.css'],
})
export class ModifierUtilisateurComponent {
  idUtilisateur: number = 0; // ID de l'utilisateur à modifier
  utilisateur: any = null; // Objet utilisateur chargé pour modification
  message: string = ''; // Message de confirmation ou d'erreur

  constructor(private utilisateurService: UtilisateurService) {}

  // Charger les données de l'utilisateur à modifier
  chargerUtilisateur(): void {
    if (!this.idUtilisateur) {
      this.message = 'Veuillez entrer un ID valide.';
      return;
    }

    this.utilisateurService.getUtilisateurById(this.idUtilisateur).subscribe({
      next: (data) => {
        this.utilisateur = data;
        this.message = '';
      },
      error: (err) => {
        console.error('Erreur lors du chargement de l\'utilisateur :', err);
        this.message = 'Utilisateur introuvable.';
      }
    });
  }

  // Modifier l'utilisateur
  modifierUtilisateur(): void {
    if (!this.utilisateur) {
      this.message = 'Veuillez charger un utilisateur avant de soumettre.';
      return;
    }

    this.utilisateurService.updateUtilisateur(this.idUtilisateur, this.utilisateur).subscribe({
      next: () => {
        this.message = 'Utilisateur modifié avec succès.';
      },
      error: (err) => {
        console.error('Erreur lors de la modification :', err);
        this.message = 'Erreur lors de la modification. Veuillez réessayer.';
      }
    });
  }
}
