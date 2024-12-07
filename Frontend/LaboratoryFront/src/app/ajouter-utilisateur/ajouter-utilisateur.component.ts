import { Component } from '@angular/core';
import { UtilisateurService } from '../services/utilisateur.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ajouter-utilisateur',
  standalone: true,
  templateUrl: './ajouter-utilisateur.component.html',
  styleUrls: ['./ajouter-utilisateur.component.css'],
  imports: [FormsModule], // Import FormsModule ici
})
export class AjouterUtilisateurComponent {
  utilisateur: any = {
    nomComplet: '',
    email: '',
    profession: '',
    numTel: '',
    signature: null,
    role: '',
    fkIdLaboratoire: null,
  };

  constructor(private utilisateurService: UtilisateurService) {}

  // Gestionnaire d'événements pour la sélection de fichier
  onFileSelect(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.utilisateur.signature = file;
    }
  }

  // Soumission du formulaire
  onSubmit(): void {
    if (this.utilisateur.signature) {
      const reader = new FileReader();
      reader.onload = () => {
        const utilisateurCompletDTO = {
          utilisateur: {
            ...this.utilisateur,
            signatureBase64: reader.result,
          },
        };

        this.utilisateurService.createUtilisateur(utilisateurCompletDTO).subscribe({
          next: (response) => {
            console.log('Utilisateur créé avec succès :', response);
            alert('Utilisateur ajouté avec succès !');
          },
          error: (error) => {
            console.error('Erreur lors de la création de l’utilisateur :', error);
            alert('Échec de l’ajout de l’utilisateur.');
          },
        });
      };
      reader.readAsDataURL(this.utilisateur.signature);
    } else {
      alert('Veuillez sélectionner un fichier de signature.');
    }
  }
}
