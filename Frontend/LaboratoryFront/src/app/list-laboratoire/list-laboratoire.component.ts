import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LaboratoireService, Laboratoire } from '../laboratoire.service';
import { MatCardModule } from '@angular/material/card';
import { MatDialogActions, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LaboratoireEditDialogComponent } from './laboratoire-edit-dialog/laboratoire-edit-dialog.component';
import { LaboratoireDetailsDialogComponent } from './laboratoire-details-dialog/laboratoire-details-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBar } from '@angular/material/snack-bar'; 
import { SideBarSuperAdminComponent } from '../side-bar-super-admin/side-bar-super-admin.component';
@Component({
  selector: 'app-laboratoire-list',
  templateUrl: './list-laboratoire.component.html',
  styleUrls: ['./list-laboratoire.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatSelectModule,
    SideBarSuperAdminComponent,
  ]
})
export class LaboratoireListComponent implements OnInit {
  logoFile: File | null = null; 
  laboratoires: Laboratoire[] = []; // Data for UI
  allLaboratoires: Laboratoire[] = []; // Cache of the full data set
  errorMessage: string | null = null;

  constructor(
    private laboratoireService: LaboratoireService,
    private dialog: MatDialog,
    private router: Router,
    private snackBar: MatSnackBar 
  ) {}


  navigateToAjouter() {
    this.router.navigate(['/add']);
  }

  ngOnInit(): void {
    this.fetchLaboratoires();
  }

  /**
   * Fetches all laboratoires and initializes `allLaboratoires`.
   */
  fetchLaboratoires(): void {
    this.laboratoireService.getAllLaboratoires().subscribe(
      (data) => {
        this.allLaboratoires = data; // Store the full dataset
        this.laboratoires = [...this.allLaboratoires]; // Set initial data
        this.errorMessage = null;
      },
      (error) => {
        console.error('Error fetching laboratories', error);
        this.errorMessage = `Erreur de chargement : ${error.message || 'Erreur inconnue'}`;
      }
    );
  }

  /**
   * Handles search input logic by filtering `allLaboratoires`.
   * Ensures server data remains cached and uses it directly for better performance.
   * @param searchTerm - The string typed by the user in search input
   */
  onSearch(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    const searchTerm = inputElement.value.toLowerCase();
    if (searchTerm) {
      this.laboratoires = this.allLaboratoires.filter(laboratoire =>
        laboratoire.nom.toLowerCase().includes(searchTerm)
      );
      if (this.laboratoires.length === 0) {
        this.errorMessage = 'Aucun laboratoire correspondant trouvé.';
      } else {
        this.errorMessage = null;
      }
    } else {
      // Reset to full list if input is cleared
      this.laboratoires = [...this.allLaboratoires];
      this.errorMessage = null;
    }
  }

  /**
   * Show details of a laboratory
   * @param laboratoire laboratory to show details for
   */
  showDetails(laboratoireId: number) {
    if (!laboratoireId) {
      console.error('Invalid laboratoireId:', laboratoireId);
      alert('Invalid laboratoire ID.');
      return;
    }
  
    this.laboratoireService.getLaboratoireById(laboratoireId).subscribe({
      next: (data) => {
        this.dialog.open(LaboratoireDetailsDialogComponent, {
          data,
          width: '800px',
          maxHeight: '90vh'
        });
      },
      error: (error) => {
        console.error('Error fetching laboratoire details:', error);
        alert('Erreur lors de la récupération des détails');
      }
    });
  }
  

  openEditDialog(laboratoire: Laboratoire): void {
    const dialogRef = this.dialog.open(LaboratoireEditDialogComponent, {
      data: laboratoire,
      width: '1200px', // Adjusted width for better content visibility
      height: '800px', // Adjusted height
    });
  
    dialogRef.afterClosed().subscribe({
      next: (result) => {
        if (result) {
          this.updateLaboratoireInList(result);
        }
      },
      error: (error) => {
        console.error('Error while closing the dialog', error);
      },
    });
  }
  
  

  /**
   * Updates a laboratoire in the lists after editing in the dialog
   * @param updatedLab - Updated laboratoire data from dialog
   */
  updateLaboratoireInList(updatedLab: Laboratoire) {
    const indexInLaboratoires = this.laboratoires.findIndex(lab => lab.id === updatedLab.id);
    const indexInAllLaboratoires = this.allLaboratoires.findIndex(lab => lab.id === updatedLab.id);

    if (indexInLaboratoires !== -1) {
      this.laboratoires[indexInLaboratoires] = updatedLab;
    }

    if (indexInAllLaboratoires !== -1) {
      this.allLaboratoires[indexInAllLaboratoires] = updatedLab;
    }
  }


 

  /**
   * Delete a laboratory
   * @param laboratoire laboratory to delete
   */
  deleteLaboratoire(laboratoire: Laboratoire) {
    const confirmDelete = confirm(`Êtes-vous sûr de vouloir supprimer ${laboratoire.nom}?`);
    if (confirmDelete) {
      this.laboratoireService.deleteLaboratoire(laboratoire.id).subscribe(
        () => {
          // Update local arrays
          this.laboratoires = this.laboratoires.filter(lab => lab.id !== laboratoire.id);
          this.allLaboratoires = this.allLaboratoires.filter(lab => lab.id !== laboratoire.id);
  
          // Notify the user
          alert('Laboratoire supprimé avec succès');
          console.log(`Laboratoire supprimé avec ID: ${laboratoire.id}`);
        },
        (error) => {
          // Log and notify about the error
          console.error('Erreur lors de la suppression du laboratoire:', error);
          alert('Erreur lors de la suppression du laboratoire.');
        }
      );
    }
  }

  updatePartiellementLaboratoire(laboratoire: Partial<Laboratoire>): void {
    if (!laboratoire.id) {
      this.snackBar.open('Identifiant du laboratoire manquant', 'Fermer', { duration: 3000 });
      return;
    }
  
    this.laboratoireService.updateLaboratoireParcellement(
      laboratoire.id, 
      laboratoire, 
      this.logoFile // Optional logo file
    ).subscribe({
      next: (updatedLaboratoire) => {
        // Update local list or show success message
        this.snackBar.open('Mise à jour réussie', 'Fermer', { duration: 3000 });
        
        // Update the local list of laboratoires
        this.updateLaboratoireInList(updatedLaboratoire);
      },
      error: (error) => {
        console.error('Erreur de mise à jour', error);
        this.snackBar.open('Erreur de mise à jour', 'Fermer', { duration: 3000 });
      }
    });
  }
  }