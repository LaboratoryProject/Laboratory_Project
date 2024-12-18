import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AnalyseService } from '../analyse.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-consulter-analyse',
  standalone:true,
  templateUrl: './consulter-analyse.component.html',
  styleUrls: ['./consulter-analyse.component.css']
})
export class ConsulterAnalyseComponent implements OnInit {
  analyseId!: number; // ID de l'analyse
  analyseData: any = {}; // Données de l'analyse
  epreuvesHtml: string = ''; // Contenu HTML des épreuves associées

  constructor(private route: ActivatedRoute, private analyseService: AnalyseService) {}

  ngOnInit(): void {
    // Récupérer l'ID de l'analyse depuis les paramètres de l'URL
    this.analyseId = +this.route.snapshot.paramMap.get('id')!;
    this.fetchAnalyseDetails(this.analyseId);
  }

  fetchAnalyseDetails(id: number): void {
    this.analyseService.getAnalyseById(id).subscribe(
      (data) => {
        this.analyseData = data;
        this.generateEpreuvesHtml(data.epreuves || []);
      },
      (error) => {
        console.error('Erreur lors de la récupération des détails de l\'analyse:', error);
      }
    );
  }

  generateEpreuvesHtml(epreuves: any[]): void {
    let htmlContent = '';
    epreuves.forEach((epreuve) => {
      htmlContent += `
        <tr>
          <td>${epreuve.id}</td>
          <td>${epreuve.type}</td>
          <td>${epreuve.date}</td>
        </tr>`;
    });
    this.epreuvesHtml = htmlContent;
  }

  revenir(): void {
    history.back();
  }
}
