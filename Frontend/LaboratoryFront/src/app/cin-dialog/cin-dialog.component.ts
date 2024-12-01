// cin-dialog.component.ts
import { Component } from '@angular/core';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';  // Nécessaire pour ngModel
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-cin-dialog',
  templateUrl: './cin-dialog.component.html',
  styleUrls: ['./cin-dialog.component.css'],
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule,MatIconModule, MatDialogModule],  // Importez les modules nécessaires ici
})
export class CinDialogComponent {




  cin: string = '';  // Initialiser la variable CIN
  isValidated: boolean = false;  // Variable pour savoir si le CIN a été validé

  constructor(public dialogRef: MatDialogRef<CinDialogComponent>) {}


  onSubmit() {
    throw new Error('Method not implemented.');
    }
    
  // Fonction pour valider le CIN
  validateCin() {
    if (this.cin) {
      // Marquer le CIN comme validé
      this.isValidated = true;
    }
  }

  // Fonction pour fermer le dialogue
  closeDialog() {
    this.dialogRef.close();
  }
}
