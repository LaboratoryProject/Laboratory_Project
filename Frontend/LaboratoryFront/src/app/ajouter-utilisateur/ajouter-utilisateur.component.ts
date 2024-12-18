import { Component, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { UserService } from '../security-management/user.service';
import { FormsModule } from '@angular/forms';
import SignaturePad from 'signature_pad'; // Importation de la bibliothèque
import { SideBarSuperAdminComponent } from '../side-bar-super-admin/side-bar-super-admin.component';

@Component({
  selector: 'app-ajouter-utilisateur',
  standalone: true,
  templateUrl: './ajouter-utilisateur.component.html',
  styleUrls: ['./ajouter-utilisateur.component.css'],
  imports: [FormsModule, SideBarSuperAdminComponent], // Importation du module Forms
})
export class AjouterUtilisateurComponent implements AfterViewInit {
  utilisateurDTO: any = {
    nomComplet: '',
    email: '',
    profession: '',
    numTel: '',
    signature: '', // Signature sous forme de base64
    role: '',
    nrc: '',
    password: '',
  };

  @ViewChild('signatureCanvas') signatureCanvas!: ElementRef; // Référence au canvas
  signaturePad!: SignaturePad; // Instance de SignaturePad
  signatureImg!: string; // Signature capturée en base64

  constructor(private userService: UserService) {}

  ngAfterViewInit(): void {
    const canvas = this.signatureCanvas.nativeElement;
    this.signaturePad = new SignaturePad(canvas);

    // Configuration du signature pad
    this.signaturePad.penColor = 'black';
    this.signaturePad.minWidth = 2;
    this.signaturePad.maxWidth = 4;
  }

  // Vérifie si la signature est vide
  isSignatureEmpty() {
    return this.signaturePad.isEmpty();
  }

  // Sauvegarder la signature en base64
  savePad() {
    if (this.signaturePad.isEmpty()) {
      alert('Veuillez fournir une signature.');
    } else {
      this.signatureImg = this.signaturePad.toDataURL(); // Convertir la signature en base64
      console.log('Signature capturée en Base64 :', this.signatureImg);
      this.utilisateurDTO.signature = this.signatureImg; // Enregistrez la signature dans l'objet utilisateur
    }
  }

  // Effacer la signature
  clearPad() {
    this.signaturePad.clear();
  }

  // Soumettre le formulaire
  onSubmit(): void {
    if (this.isSignatureEmpty()) {
      alert('Veuillez fournir une signature.');
      return;
    }
  
    // Ajout de la signature au modèle utilisateur
    this.utilisateurDTO.signature = this.signaturePad.toDataURL(); // Conversion de la signature en base64
  
    // Vérification des données avant l'envoi
    console.log('Données à envoyer :', this.utilisateurDTO);
    console.log('nrc à envoyer :', this.utilisateurDTO.nrc);
  
    // Vérification de l'email et du mot de passe
    if (!this.utilisateurDTO.email || !this.utilisateurDTO.password) {
      alert('L\'email et le mot de passe sont obligatoires.');
      return;
    }
  
    // Envoi des données à l'API
    this.userService.createUtilisateur(this.utilisateurDTO).subscribe({
      next: (response) => {
        console.log('Utilisateur créé avec succès :', response);
        alert('Utilisateur ajouté avec succès !');
      },
      error: (error) => {
        console.error('Erreur lors de la création de l’utilisateur :', error);
        alert('Échec de l’ajout de l’utilisateur.');
      },
    });
  }
  
}
