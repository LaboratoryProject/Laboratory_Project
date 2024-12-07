import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Analyse } from '../models/analyse.model';  // Assure-toi d'avoir ce modèle

@Injectable({
  providedIn: 'root',
})
export class AnalyseService {
  private baseUrl = 'http://localhost:8082/analyses'; // URL de l'API pour les analyses

  constructor(private http: HttpClient) {}

  // Récupérer toutes les analyses
  getAllAnalyses(): Observable<Analyse[]> {
    return this.http.get<Analyse[]>(`${this.baseUrl}`);
  }

  // Récupérer une analyse par ID
  getAnalyseById(id: number): Observable<Analyse> {
    return this.http.get<Analyse>(`${this.baseUrl}/${id}`);
  }

  // Ajouter une analyse
  createAnalyse(data: Analyse): Observable<Analyse> {
    return this.http.post<Analyse>(`${this.baseUrl}`, data);
  }

  // Mettre à jour une analyse
  updateAnalyse(id: number, data: Analyse): Observable<Analyse> {
    return this.http.put<Analyse>(`${this.baseUrl}/${id}`, data);
  }

  // Supprimer une analyse
  deleteAnalyse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
