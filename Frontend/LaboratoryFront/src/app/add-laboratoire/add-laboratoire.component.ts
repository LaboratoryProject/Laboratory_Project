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

  onSubmit(): void {
    if (this.laboratoire.logo) {
      const formData = new FormData();
  
      // Create a DTO object that matches your backend expectation
      const laboratoireCompletDTO = {
        laboratoire: {
          nom: this.laboratoire.nom,
          nrc: this.laboratoire.nrc,
          active: this.laboratoire.active,
          dateActivation: this.laboratoire.dateActivation
        },
        contactLaboratoire: {
          numTel: this.laboratoire.numTel,
          fax: this.laboratoire.fax,
          email: this.laboratoire.email
        },
        adresse: {
          numVoie: this.laboratoire.numVoie,
          nomVoie: this.laboratoire.nomVoie,
          codePostal: this.laboratoire.codePostal,
          ville: this.laboratoire.ville,
          commune: this.laboratoire.commune
        }
      };
      console.log(laboratoireCompletDTO)
  
      // Append the DTO as a JSON string
      formData.append('laboratoireCompletDTO', new Blob([JSON.stringify(laboratoireCompletDTO)], { type: 'application/json' }));
      
      // Append logo file to FormData
      formData.append('logoFile', this.laboratoire.logo);
  
      // Send the form data to the backend
      this.laboratoireService.createLaboratoire(formData).subscribe({
        next: (response) => {
          console.log('Laboratoire créé avec succès:', response);
          alert('Laboratoire ajouté avec succès !');
        },
        error: (error) => {
          console.error('Erreur lors de la création du laboratoire:', error);
          alert('Échec de lajout du laboratoire.');
        }
      });
    } else {
      alert('Veuillez sélectionner un fichier logo.');
    }
  }
}
