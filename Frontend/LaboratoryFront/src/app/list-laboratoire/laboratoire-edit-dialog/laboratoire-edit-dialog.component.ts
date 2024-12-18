import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import {
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { LaboratoireService } from '../../laboratoire.service';

export interface Laboratoire {
  id?: number; // Optional id for updates
  nom: string;
  logo: string | null; // Changed logo to always be a string (URL)
  nrc: string;
  active: boolean;
  dateActivation: string;
  numTel: string;
  fax: string;
  email: string;
  numVoie: string;
  nomVoie: string;
  codePostal: string;
  ville: string;
  commune: string;
}

@Component({
  selector: 'app-laboratoire-edit-dialog',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule
  ],
  templateUrl: './laboratoire-edit-dialog.component.html',
  styleUrls: ['./laboratoire-edit-dialog.component.css']
})
export class LaboratoireEditDialogComponent {
  laboratoireData: Laboratoire;
  logoFile: File | null = null;
  logoPreview: string | null = null;

  constructor(
    public dialogRef: MatDialogRef<LaboratoireEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Laboratoire,
    private laboratoireService: LaboratoireService
  ) {
    // Create a deep copy of data to prevent unintended mutations
    this.laboratoireData = { ...data };
  }

  // Close dialog
  onNoClick(): void {
    this.dialogRef.close();
  }

  // Handle logo file selection and preview logic
  onFileSelect(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
  
    if (file) {
      const validImageTypes = ['image/jpeg', 'image/png', 'image/gif'];
      const maxSize = 5 * 1024 * 1024; // Limit size to 5MB
  
      if (!validImageTypes.includes(file.type)) {
        alert('Seules les images JPEG, PNG et GIF sont autorisées');
        input.value = '';
        return;
      }
  
      if (file.size > maxSize) {
        alert('La taille du fichier ne doit pas dépasser 5 Mo');
        input.value = '';
        return;
      }
  
      this.logoFile = file; // Store the actual file
      const reader = new FileReader();
      reader.onload = (e: ProgressEvent<FileReader>) => {
        this.logoPreview = e.target?.result as string | null;
      };
      reader.readAsDataURL(file);
    }
  }
  

  // Handle the submission logic
  updatePartiellementLaboratoire(): void {
    if (!this.laboratoireData.id) {
      alert('L\'identifiant du laboratoire est manquant');
      return;
    }

    // Use the service to update partially
    this.laboratoireService.updateLaboratoireParcellement(
      this.laboratoireData.id, 
      this.laboratoireData, 
      this.logoFile
    ).subscribe({
      next: (response) => {
        console.log('Update successful', response);
        // Close the dialog and pass back the updated laboratoire
        this.dialogRef.close(response);
      },
      error: (error) => {
        console.error('Update failed', error);
        alert('Erreur de mise à jour');
      }
    });
  }
  
}