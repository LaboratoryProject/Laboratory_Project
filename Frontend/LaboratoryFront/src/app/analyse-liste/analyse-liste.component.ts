import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { AnalyseService} from '../analyse.service';

@Component({
  selector: 'app-analyse-liste',
  templateUrl: './analyse-liste.component.html',
  standalone:true,
  styleUrls: ['./analyse-liste.component.css'],
  imports: [
    CommonModule,
  ]
})
export class AnalyseListeComponent implements OnInit {
  // Liste d'analyses fictives
  analyses = [
    {
            id: 1,
            titre: 'Analyse de sang',
            date: '2023-10-12',
            patient: 'Ahmed El Idrissi',
            description: 'Analyse complète des globules rouges et blancs',
            laboratoire: 'Lab Rabat',
            statut: 'en cours'
        },
        {
            id: 2,
            titre: 'Analyse d’urine',
            date: '2023-10-15',
            patient: 'Fatima Bensalah',
            description: 'Analyse des infections urinaires',
            laboratoire: 'Lab Casablanca',
            statut: 'terminé'
        },
        {
            id: 3,
            titre: 'Analyse biochimique',
            date: '2023-11-01',
            patient: 'Youssef Amrani',
            description: 'Analyse approfondie des enzymes et des lipides',
            laboratoire: 'Lab Marrakech',
            statut: 'en cours'
        },
        {
            id: 4,
            titre: 'Analyse génétique',
            date: '2023-12-03',
            patient: 'Salma Othmani',
            description: 'Analyse ADN pour détecter des mutations',
            laboratoire: 'Lab Fès',
            statut: 'terminé'
        },
        {
            id: 5,
            titre: 'Analyse hormonale',
            date: '2023-12-08',
            patient: 'Mohammed Chraibi',
            description: 'Analyse des niveaux hormonaux',
            laboratoire: 'Lab Tanger',
            statut: 'en cours'
        },
        {
            id: 6,
            titre: 'Analyse immunologique',
            date: '2023-12-10',
            patient: 'Hanae Zaim',
            description: 'Analyse des défenses immunitaires',
            laboratoire: 'Lab Agadir',
            statut: 'terminé'
        },
  ];

  filteredAnalyses = [...this.analyses];
  searchTerm: string = '';

 constructor(private router: Router) {}


    navigateToAjouter() {
       this.router.navigate(['/ajouter-analyse']);
     }

   navigateToConsulter() {
          this.router.navigate(['/consulter-analyse']);
        }

 navigateToModifier() {
       this.router.navigate(['/modifier-analyse']);
     }


  ngOnInit(): void {
    // Logique d'initialisation si nécessaire
  }


 onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.filteredAnalyses = this.analyses.filter((analyse) =>
      analyse.titre.toLowerCase().includes(searchTerm) ||
      analyse.patient.toLowerCase().includes(searchTerm) ||
      analyse.date.includes(searchTerm)
    );
  }

  applyFilter(): void {
    this.filteredAnalyses = this.analyses.filter((analyse) =>
      analyse.titre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      analyse.laboratoire.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      analyse.statut.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }


  confirmDelete(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette analyse ?')) {
      this.analyses = this.analyses.filter((analyse) => analyse.id !== id);
      this.applyFilter();
      console.log('Analyse supprimée avec ID:', id);
    }
  }

  viewAnalyse(id: number): void {
    console.log('Consulter analyse avec ID:', id);
    // Logique pour afficher une vue détaillée
  }
  exportAnalyses(): void {
    const csvContent = [
      ['ID', 'Titre', 'Date', 'Patient', 'Description', 'Laboratoire', 'Statut'], // Entêtes
      ...this.analyses.map((analyse) => [
        analyse.id,
        analyse.titre,
        analyse.date,
        analyse.patient,
        analyse.description,
        analyse.laboratoire,
        analyse.statut,
      ]),
    ]
      .map((row) => row.join(',')) // Convertir chaque ligne en CSV
      .join('\n'); // Joindre les lignes

    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'analyses.csv'; // Nom du fichier exporté
    a.click();
    window.URL.revokeObjectURL(url);
    alert('Analyses exportées avec succès !');
  }
}
