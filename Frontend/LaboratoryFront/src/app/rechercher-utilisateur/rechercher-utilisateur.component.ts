import { Component } from '@angular/core';
import { UtilisateurService } from '../services/utilisateur.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-rechercher-utilisateur',
  standalone: true,
  templateUrl: './rechercher-utilisateur.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./rechercher-utilisateur.component.css'],
})
export class RechercherUtilisateurComponent {
  idUtilisateur: number = 0; // ID à rechercher
  role: string = ''; // Rôle à rechercher
  utilisateur: any = null; // Résultat d'une recherche par ID
  utilisateurs: any[] = []; // Résultats d'une recherche par rôle
  message: string = ''; // Message d'erreur ou d'information

  constructor(private utilisateurService: UtilisateurService) {}

  // Rechercher un utilisateur par ID
  rechercherParId(): void {
    if (!this.idUtilisateur) {
      this.message = "Veuillez entrer un ID valide.";
      this.utilisateur = null;
      return;
    }

    this.utilisateurService.getUtilisateurById(this.idUtilisateur).subscribe({
      next: (data) => {
        this.utilisateur = data;
        this.utilisateurs = [];
        this.message = '';
      },
      error: (err) => {
        console.error('Erreur lors de la recherche par ID :', err);
        this.message = "Utilisateur introuvable.";
        this.utilisateur = null;
      }
    });
  }

  // Rechercher des utilisateurs par rôle
  rechercherParRole(): void {
    if (!this.role) {
      this.message = "Veuillez sélectionner un rôle.";
      this.utilisateurs = [];
      return;
    }

    this.utilisateurService.getUtilisateursByRole(this.role).subscribe({
      next: (data) => {
        this.utilisateurs = data;
        this.utilisateur = null;
        this.message = '';
      },
      error: (err) => {
        console.error('Erreur lors de la recherche par rôle :', err);
        this.message = "Aucun utilisateur trouvé pour ce rôle.";
        this.utilisateurs = [];
      }
    });
  }
}
