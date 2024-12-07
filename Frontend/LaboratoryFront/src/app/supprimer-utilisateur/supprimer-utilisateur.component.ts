import { Component } from '@angular/core';
import { UtilisateurService } from '../services/utilisateur.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-supprimer-utilisateur',
  standalone: true,
  templateUrl: './supprimer-utilisateur.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./supprimer-utilisateur.component.css'],
})
export class SupprimerUtilisateurComponent {
  idUtilisateur: number = 0; // ID de l'utilisateur à supprimer
  message: string = ''; // Message de confirmation ou d'erreur

  constructor(private utilisateurService: UtilisateurService) {}

  supprimerUtilisateur(): void {
    if (!this.idUtilisateur) {
      this.message = "Veuillez entrer un ID valide.";
      return;
    }

    if (confirm("Êtes-vous sûr de vouloir supprimer cet utilisateur ?")) {
      this.utilisateurService.deleteUtilisateur(this.idUtilisateur).subscribe({
        next: () => {
          this.message = `Utilisateur avec l'ID ${this.idUtilisateur} supprimé avec succès.`;
          this.idUtilisateur = 0; // Réinitialiser l'ID
        },
        error: (err) => {
          console.error('Erreur lors de la suppression :', err);
          this.message = "Erreur lors de la suppression. Veuillez réessayer.";
        }
      });
    }
  }
}
