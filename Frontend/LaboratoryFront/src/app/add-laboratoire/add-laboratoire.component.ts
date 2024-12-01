import { Component } from '@angular/core';
import { LaboratoireService } from '../laboratoire.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-laboratoire',
  templateUrl: './add-laboratoire.component.html',
  styleUrls: ['./add-laboratoire.component.css'],
  imports: [FormsModule], // Import FormsModule here
})
export class AddLaboratoireComponent {
  laboratoire: any = {
    nom: '',
    logo: null,
    nrc: '',
    active: false,
    dateActivation: '',
    numTel: '',
    fax: '',
    email: '',
    numVoie: '',
    nomVoie: '',
    codePostal: '',
    ville: '',
    commune: '',
  };

  constructor(private laboratoireService: LaboratoireService) {}

  // Event handler for file selection
  onFileSelect(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.laboratoire.logo = file;
    }
  }

  // Form submission logic
  onSubmit(): void {
    if (this.laboratoire.logo) {
      const reader = new FileReader(); // Create a FileReader instance
      reader.onload = () => {
        // Base64 conversion completed
        const laboratoireCompletDTO = {
          laboratoire: {
            nom: this.laboratoire.nom,
            nrc: this.laboratoire.nrc,
            active: this.laboratoire.active,
            dateActivation: this.laboratoire.dateActivation,
            logoBase64: reader.result, // Base64 representation of the logo
          },
          contactLaboratoire: {
            numTel: this.laboratoire.numTel,
            fax: this.laboratoire.fax,
            email: this.laboratoire.email,
          },
          adresse: {
            numVoie: this.laboratoire.numVoie,
            nomVoie: this.laboratoire.nomVoie,
            codePostal: this.laboratoire.codePostal,
            ville: this.laboratoire.ville,
            commune: this.laboratoire.commune,
          },
        };

        // Send the JSON object to the backend
        this.laboratoireService.createLaboratoire(laboratoireCompletDTO).subscribe({
          next: (response) => {
            console.log('Laboratoire créé avec succès :', response);
            alert('Laboratoire ajouté avec succès !');
          },
          error: (error) => {
            console.error('Erreur lors de la création du laboratoire :', error);
            alert('Échec de l’ajout du laboratoire.');
          },
        });
      };
      // Read the logo file as a Base64 string
      reader.readAsDataURL(this.laboratoire.logo);
    } else {
      alert('Veuillez sélectionner un fichier logo.');
    }
  }
}
