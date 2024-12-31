import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TestAnalyseService } from '../test-analyse-service.service';
import { TestAnalyse } from '../test-analyse.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-test-analyse-liste',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './test-analyse-liste.component.html',
  styleUrls: ['./test-analyse-liste.component.css']
})
export class TestAnalyseListeComponent implements OnInit {
confirmDelete(arg0: number|undefined) {
throw new Error('Method not implemented.');
}
  testAnalyses: TestAnalyse[] = []; // Liste complète des tests
  filteredTestAnalyses: TestAnalyse[] = []; // Liste filtrée pour la recherche
  searchQuery: string = ''; // Requête de recherche

  constructor(private TestAnalyseService: TestAnalyseService, private router: Router) {}

  ngOnInit(): void {
    this.fetchTestAnalyses(); // Récupère les données au chargement
  }

  // Récupère tous les tests d'analyse depuis le backend
  fetchTestAnalyses(): void {
    this.TestAnalyseService.getAllTestAnalyses().subscribe(
      (data: TestAnalyse[]) => {
        this.testAnalyses = data;
        this.filteredTestAnalyses = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des tests :', error);
      }
    );
  }

  // Filtre les tests en fonction de la recherche
  onSearch(event: Event): void {
    const query = (event.target as HTMLInputElement).value.toLowerCase();
    this.filteredTestAnalyses = this.testAnalyses.filter(test =>
      test.name.toLowerCase().includes(query) ||
      test.unite.toLowerCase().includes(query)
    );
  }

  // Navigue vers la page pour ajouter un test
  navigateToAjouter(): void {
    this.router.navigate(['/test-analyse/ajouter']);
  }

  navigateToModifier(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate([`/modifier/${id}`]);
    } else {
      console.error('ID is undefined');
    }
  }
  
  navigateToConsulter(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate([`/consulter/${id}`]);
    } else {
      console.error('ID is undefined');
    }
  }
  
  
  

  // Confirme et supprime un test d'analyse
 

  // Exporte la liste des tests (simulation)
  exportTestAnalyses(): void {
    console.log('Exportation des tests en cours...');
    // Implémenter la logique d'exportation si nécessaire
  }
}
