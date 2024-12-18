import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TestAnalyseService } from '../test-analyse.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-test-analyse-liste',
  standalone:true,
  imports: [CommonModule, FormsModule ],
  templateUrl: './test-analyse-liste.component.html',
  styleUrls: ['./test-analyse-liste.component.css']
})
export class TestAnalyseListeComponent implements OnInit {
  testAnalyses = [
    {
      id: 1,
      nom: 'Test Sanguin',
      valeurMin: 10,
      valeurMax: 200,
      unite: 'g/L',
      analyse: 'Analyse de sang',
      epreuve: 'Prise de sang'
    },
    {
      id: 2,
      nom: 'Test Urinaire',
      valeurMin: 4,
      valeurMax: 10,
      unite: 'mmol/L',
      analyse: 'Analyse d’urine',
      epreuve: 'Examen urinaire'
    }
    // ... autres TestAnalyse
  ];

  filteredTestAnalyses = [...this.testAnalyses];
  searchTerm: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Initialisation si nécessaire
  }

  onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.filteredTestAnalyses = this.testAnalyses.filter((test) =>
      test.nom.toLowerCase().includes(searchTerm) ||
      test.analyse.toLowerCase().includes(searchTerm) ||
      test.epreuve.toLowerCase().includes(searchTerm)
    );
  }

  applyFilter(): void {
    this.filteredTestAnalyses = this.testAnalyses.filter((test) =>
      test.nom.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      test.analyse.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      test.epreuve.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  confirmDelete(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce test ?')) {
      this.testAnalyses = this.testAnalyses.filter((test) => test.id !== id);
      this.applyFilter();
      console.log('Test supprimé avec ID:', id);
    }
  }

  navigateToAjouter() {
    this.router.navigate(['/ajouter-test-analyse']);
  }

  navigateToModifier(id: number) {
    this.router.navigate(['/modifier-test-analyse', id]);
  }

  navigateToConsulter(id: number) {
    this.router.navigate(['/consulter-test-analyse', id]);
  }

  exportTestAnalyses(): void {
    const csvContent = [
      ['ID', 'Nom', 'Valeur Min', 'Valeur Max', 'Unité', 'Analyse', 'Épreuve'], // Entêtes
      ...this.testAnalyses.map((test) => [
        test.id,
        test.nom,
        test.valeurMin,
        test.valeurMax,
        test.unite,
        test.analyse,
        test.epreuve,
      ]),
    ]
      .map((row) => row.join(','))
      .join('\n');

    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'test_analyses.csv';
    a.click();
    window.URL.revokeObjectURL(url);
    alert('Tests exportés avec succès !');
  }
}
