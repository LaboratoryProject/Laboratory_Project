import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LaboratoireService, Laboratoire } from '../laboratoire.service';

@Component({
  selector: 'app-laboratoire-list',
  templateUrl: './list-laboratoire.component.html',
  styleUrls:['./list-laboratoire.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class LaboratoireListComponent implements OnInit {
  laboratoires: Laboratoire[] = [];
  errorMessage: string | null = null;

  constructor(private laboratoireService: LaboratoireService) {}

  ngOnInit(): void {
    this.fetchLaboratoires();
  }

  fetchLaboratoires(): void {
    this.laboratoireService.getAllLaboratoires().subscribe(
      (data) => {
        this.laboratoires = data;
        
        if (this.laboratoires.length === 0) {
          this.errorMessage = 'Aucun laboratoire trouvé dans la base de données.';
        }
      },
      (error) => {
        console.error('Full error:', error);
        this.errorMessage = `Erreur de chargement : ${error.message || 'Erreur inconnue'}`;
      }
    );
  }

  // Optional: Fallback image method
  getFallbackImage(imageUrl?: string): string {
    return imageUrl || 'path/to/default/image.png';
  }
}