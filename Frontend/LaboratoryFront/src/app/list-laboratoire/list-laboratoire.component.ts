import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LaboratoireService } from '../services/laboratoire.service';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Laboratoire } from '../models/laboratoire.model';

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
  ]
})
export class LaboratoireListComponent implements OnInit {
  laboratoires: Laboratoire[] = []; // Data for UI
  allLaboratoires: Laboratoire[] = []; // Cache of the full data set
  errorMessage: string | null = null;

  constructor(
    private laboratoireService: LaboratoireService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  navigateToAjouter() {
    this.router.navigate(['/add']);
  }

  ngOnInit(): void {
    this.fetchLaboratoires();
  }

  /**
   * Fetches all laboratoires and initializes allLaboratoires.
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
        this.errorMessage = `Erreur de chargement : ${error.message || "Erreur inconnue"}`; // Fixed string interpolation
      }
    );
  }

  /**
   * Handles search input logic by filtering allLaboratoires.
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
  showDetails(laboratoire: Laboratoire) {
    // Corrected alert message interpolation
    alert(`Détails de : ${laboratoire.nom}`);
    // Alternatively, you could open a dialog or navigate to a details page
    // this.dialog.open(LaboratoireDetailsComponent, { data: laboratoire });
  }

  /**
   * Edit a laboratory
   * @param laboratoire laboratory to edit
   */
  editLaboratoire(laboratoire: Laboratoire) {
    // Corrected alert message interpolation
    alert(`Modifier : ${laboratoire.nom}`);
    // Typically, you would open a dialog or navigate to an edit form
    // this.dialog.open(LaboratoireEditComponent, { data: laboratoire });
  }

  /**
   * Delete a laboratory
   * @param laboratoire laboratory to delete
   */
  deleteLaboratoire(laboratoire: Laboratoire) {
    if (laboratoire.id === undefined) {
      alert("ID du laboratoire est manquant.");
      return; // Si l'ID est indéfini, on arrête la suppression
    }

    const confirmDelete = confirm(`Êtes-vous sûr de vouloir supprimer ${laboratoire.nom}?`); // Fixed string interpolation
    if (confirmDelete) {
      this.laboratoireService.deleteLaboratoire(laboratoire.id).subscribe(
        () => {
          // Remove from local array
          this.laboratoires = this.laboratoires.filter(lab => lab.id !== laboratoire.id);
          this.allLaboratoires = this.allLaboratoires.filter(lab => lab.id !== laboratoire.id);
          alert('Laboratoire supprimé avec succès');
        },
        (error) => {
          console.error('Erreur lors de la suppression', error);
          alert('Erreur lors de la suppression du laboratoire');
        }
      );
    }
  }
}
