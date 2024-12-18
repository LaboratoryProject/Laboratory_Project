import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { EpreuveService } from '../epreuve.service';

@Component({
  selector: 'app-epreuve-liste',
  templateUrl: './epreuve-liste.component.html',
  styleUrls: ['./epreuve-liste.component.css'],
  imports: [
    CommonModule,
  ]
})
export class EpreuveListeComponent implements OnInit {
  // Liste fictive d'épreuves
  epreuves = [
    { id: 1, nom: 'Épreuve 1', fkIdAnalyse: 101 },
    { id: 2, nom: 'Épreuve 2', fkIdAnalyse: 102 },
    { id: 3, nom: 'Épreuve 3', fkIdAnalyse: 103 },
    { id: 4, nom: 'Épreuve 4', fkIdAnalyse: 104 },
  ];

  filteredEpreuves = [...this.epreuves];
  searchTerm: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Logique d'initialisation si nécessaire
  }

  // Barre de recherche
  onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.filteredEpreuves = this.epreuves.filter((epreuve) =>
      epreuve.nom.toLowerCase().includes(searchTerm) ||
      epreuve.fkIdAnalyse.toString().includes(searchTerm)
    );
  }

navigateToAjouter() {
       this.router.navigate(['/ajouter-epreuve']);
     }

  // Consulter une épreuve
   navigateToConsulter() {
            this.router.navigate(['/consulter-epreuve']);
          }

  // Modifier une épreuve
  navigateToModifier() {
             this.router.navigate(['/modifier-epreuve']);
           }


  // Supprimer une épreuve
  confirmDelete(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette épreuve ?')) {
      this.epreuves = this.epreuves.filter((epreuve) => epreuve.id !== id);
      this.onSearch(new Event('input')); // Réappliquer le filtre
      console.log('Épreuve supprimée avec ID:', id);
    }
  }

  // Exporter les épreuves
  exportEpreuves(): void {
    const csvContent = [
      ['ID', 'Nom', 'ID Analyse Associée'], // En-têtes
      ...this.epreuves.map((epreuve) => [
        epreuve.id,
        epreuve.nom,
        epreuve.fkIdAnalyse,
      ]),
    ]
      .map((row) => row.join(',')) // Convertir chaque ligne en CSV
      .join('\n'); // Joindre les lignes

    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'epreuves.csv'; // Nom du fichier exporté
    a.click();
    window.URL.revokeObjectURL(url);
    alert('Épreuves exportées avec succès !');
  }
}
